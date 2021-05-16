package com.example.mobishop.common.di.providers

import com.example.mobishop.data.sources.room.MobiShopDao
import com.example.mobishop.data.sources.room.MobiShopDatabase

class DaoProvider(private val mobiShopDatabase: MobiShopDatabase) {
    fun provideMobiShopDao(): MobiShopDao = mobiShopDatabase.mobiShopDao()
}