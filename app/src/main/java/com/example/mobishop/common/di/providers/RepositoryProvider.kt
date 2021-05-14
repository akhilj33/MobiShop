package com.example.mobishop.common.di.providers

import com.example.mobishop.data.repository.MobiRepository
import com.example.mobishop.data.repository.MobiRepositoryImpl

class RepositoryProvider(
    private val apiSourceProvider: ApiSourceProvider
) {

    private val mobiRepository by lazy {
        MobiRepositoryImpl(apiSourceProvider.provideMobiShopSource())
    }

    fun provideMobiRepository(): MobiRepository = mobiRepository
}