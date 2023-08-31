package com.example.sampledemoapp.init

import android.app.Application
import com.example.sampledemoapp.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DemoNutApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@DemoNutApplication)
            modules(appModule)
        }
    }
}