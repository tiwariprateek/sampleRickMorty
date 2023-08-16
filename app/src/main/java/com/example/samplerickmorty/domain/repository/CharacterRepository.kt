package com.example.samplerickmorty.domain.repository

import com.example.samplerickmorty.data.CharacterResponse
import com.example.samplerickmorty.domain.model.CharacterListing
import com.example.samplerickmorty.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacterListing(
        fetchFromRemote:Boolean
    ): Flow<NetworkResult<List<CharacterListing>>>
}