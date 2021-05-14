package com.example.mobishop.common.di.providers

import com.example.mobishop.data.sources.MobiShopSource
import com.example.mobishop.data.sources.MobiShopSourceImpl

class ApiSourceProvider(private val retrofitProvider: RetrofitProvider) {
    fun provideMobiShopSource(): MobiShopSource =
        MobiShopSourceImpl(retrofitProvider.provideApiService())
}