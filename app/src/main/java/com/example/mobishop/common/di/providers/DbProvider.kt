package com.example.mobishop.common.di.providers

import com.example.mobishop.data.sources.room.MobiShopDbImpl

class DbProvider(private val daoProvider: DaoProvider) {
    fun provideMobiShopDb() = MobiShopDbImpl(daoProvider.provideMobiShopDao())
}