package com.example.mobishop

import android.app.Application
import com.example.mobishop.common.di.DependencyProvider

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyProvider.inject(this)
    }
}