package com.example.samplerickmorty.api

import com.example.samplerickmorty.data.CharacterResponse
import com.example.samplerickmorty.utils.NetworkResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters():Response<CharacterResponse>

    companion object{
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
        fun create():ApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}