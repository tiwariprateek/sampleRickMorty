package com.example.samplerickmorty.di

<<<<<<< HEAD
import com.example.samplerickmorty.data.remote.ApiService
=======
import com.example.samplerickmorty.api.ApiService
>>>>>>> 475730e98537abdb31f66e1508b5f7de6bb64aeb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
<<<<<<< HEAD
    fun provideApiService(): ApiService {
=======
    fun provideApiService():ApiService{
>>>>>>> 475730e98537abdb31f66e1508b5f7de6bb64aeb
        return ApiService.create()
    }

}