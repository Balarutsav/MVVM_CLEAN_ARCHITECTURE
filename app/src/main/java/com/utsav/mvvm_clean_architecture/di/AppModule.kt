package com.utsav.mvvm_clean_architecture.di

import com.utsav.mvvm_clean_architecture.data.remote.AppApiService
import com.utsav.mvvm_clean_architecture.data.repository.AppRepositoryImpl
import com.utsav.mvvm_clean_architecture.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideCoinRepository(api: AppApiService):AppRepository {
        return AppRepositoryImpl(api)
    }
}