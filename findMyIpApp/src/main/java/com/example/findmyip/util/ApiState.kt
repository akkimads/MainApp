package com.example.findmyip.util

import com.example.findmyip.network.IpInfo
import retrofit2.Response

sealed class ApiState {
    class Success(val dataSealed: Response<IpInfo>) : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    object Loading:ApiState()
    object Empty: ApiState()
    }