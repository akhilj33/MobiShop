package com.example.mobishop.common.di.providers

import android.content.Context
import com.example.mobishop.data.sources.internet.InternetSource
import com.example.mobishop.data.sources.internet.InternetSourceImpl

class InternetSourceProvider(context: Context) {
    private val internetSource: InternetSource = InternetSourceImpl(context)
    fun provideInternetSource(): InternetSource = internetSource
}
