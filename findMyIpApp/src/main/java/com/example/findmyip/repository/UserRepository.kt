package com.example.findmyip.repository

import android.util.Log
import com.example.findmyip.network.ApiService
import com.example.findmyip.network.IpInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserRepository  @Inject constructor(private val apiService: ApiService)  {
    val TAG = "user Ip repository :"

    fun fetchData(): Flow<Response<IpInfo>> = flow {
        try {
            val response = apiService.getUsers()
            Log.d(TAG,response.toString())
            emit(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

}