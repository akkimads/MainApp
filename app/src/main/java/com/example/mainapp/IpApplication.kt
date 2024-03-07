package com.example.mainapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@dagger.hilt.android.HiltAndroidApp
class IpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}