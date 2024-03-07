package com.example.findmyip.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_URL = "https://ipapi.co/"

    }
    @GET("json/")
    suspend fun getUsers(): Response<IpInfo>
}