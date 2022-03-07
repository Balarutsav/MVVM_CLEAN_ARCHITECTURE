package com.utsav.mvvm_clean_architecture.di


import androidx.viewbinding.BuildConfig
import com.google.gson.Gson

import com.utsav.mvvm_clean_architecture.common.ApiConstant
import com.utsav.mvvm_clean_architecture.data.remote.AppApiService
import com.utsav.mvvm_clean_architecture.data.remote.BaseDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstant.BASE_URL)
        .client(okHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideGSON(): Gson = Gson()


    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): AppApiService =
        retrofit.create(AppApiService::class.java)

    @Singleton
    @Provides
    fun provideBaseOutsources() = BaseDataSource()

    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {
        val levelType: HttpLoggingInterceptor.Level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original: Request = chain.request()
                val requestBuilder = original.newBuilder()

                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })


        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.addNetworkInterceptor(logging)
        val client = httpClient.build()
        return client
    }

}