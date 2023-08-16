package com.example.samplerickmorty.data.repository

import com.example.samplerickmorty.data.CharacterResponse
import com.example.samplerickmorty.data.local.CharacterDatabase
import com.example.samplerickmorty.data.mapper.toCharacter
import com.example.samplerickmorty.data.mapper.toCharacterEntity
import com.example.samplerickmorty.data.remote.ApiService
import com.example.samplerickmorty.domain.model.CharacterListing
import com.example.samplerickmorty.domain.repository.CharacterRepository
import com.example.samplerickmorty.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    val api:ApiService,
    val db:CharacterDatabase
):CharacterRepository {
    private val dao = db.dao
    override suspend fun getCharacterListing(fetchFromRemote: Boolean): Flow<NetworkResult<List<CharacterListing>>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val localCharacter = dao.getCharacter()
            emit(NetworkResult.Success(localCharacter.map { it.toCharacter() }))
            val isDbEmpty = localCharacter.isEmpty()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldLoadFromCache){
                emit(NetworkResult.Loading(false))
                return@flow
            }
            val remoteCharacter = try {
                emit(NetworkResult.Loading(true))
                val response = api.getAllCharacters()
                response.data?.let {
                    dao.clearCharacterListing()
                    dao.insertCharacterListing(it.map { it.toCharacterEntity() })
                    emit(NetworkResult.Success(data = dao.getCharacter().map { it.toCharacter() }))
                    emit(NetworkResult.Loading(false))
                }

            }catch (e:Exception){
                e.printStackTrace()
                emit(NetworkResult.Error("Couldn't load data"))
            }catch (e:HttpException){
                e.printStackTrace()
                emit(NetworkResult.Error("Couldn't load data"))

            }
        }
    }
}