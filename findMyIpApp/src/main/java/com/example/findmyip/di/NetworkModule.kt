package com.example.findmyip.di

import com.example.findmyip.network.ApiService
import com.example.findmyip.network.ApiService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideClient() : OkHttpClient {
        val logginInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient
            .Builder()
            .addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                HttpLoggingInterceptor.Level.BODY
                return@Interceptor chain.proceed(builder.build())
            })
            .addInterceptor(logginInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient())
            .build()
    }

    @Singleton
    @Provides
    fun movieApi(retrofit : Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }
}