package com.example.samplerickmorty.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.samplerickmorty.api.ApiService
import com.example.samplerickmorty.data.CharacterResponse
import com.example.samplerickmorty.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(val apiService: ApiService) {

    private val _characterResponse = MutableLiveData<NetworkResult<CharacterResponse>>()
    val characterResponse : LiveData<NetworkResult<CharacterResponse>> get() = _characterResponse


    suspend fun getCharacters() = handleResponse(apiService.getAllCharacters())


    private fun handleResponse(response: Response<CharacterResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _characterResponse.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _characterResponse.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _characterResponse.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}