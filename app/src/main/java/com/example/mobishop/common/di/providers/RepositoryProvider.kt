package com.example.mobishop.common.di.providers

import com.example.mobishop.data.repository.MobiRepository
import com.example.mobishop.data.repository.MobiRepositoryImpl

class RepositoryProvider(
    private val apiSourceProvider: ApiSourceProvider,
    private val internetSourceProvider: InternetSourceProvider,
    private val dbProvider: DbProvider
) {

    private val mobiRepository by lazy {
        MobiRepositoryImpl(
            apiSourceProvider.provideMobiShopSource(),
            internetSourceProvider.provideInternetSource(), dbProvider.provideMobiShopDb()
        )
    }

    fun provideMobiRepository(): MobiRepository = mobiRepository
}