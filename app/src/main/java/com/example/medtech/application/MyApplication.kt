package com.example.medtech.application

import android.app.Application
import com.example.medtech.koin.retrofitModule
import com.example.medtech.koin.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(retrofitModule, viewModules))
            androidContext(this@MyApplication)
        }
    }
}