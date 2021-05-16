package com.example.mobishop.common.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.mobishop.common.di.providers.*
import com.example.mobishop.data.sources.room.MobiShopDatabase

object DependencyProvider {

    private lateinit var applicationContext: Context

    fun inject(context: Context) {
        applicationContext = context
    }

    private val internetSourceProvider: InternetSourceProvider by lazy {
        InternetSourceProvider(provideApplicationContext())
    }

    private val retrofitProvider: RetrofitProvider by lazy {
        RetrofitProvider()
    }

    private val daoProvider: DaoProvider by lazy {
        DaoProvider(mobiShopDatabase)
    }

    private val dbProvider: DbProvider by lazy {
        DbProvider(daoProvider)
    }

    private val apiSourceProvider: ApiSourceProvider by lazy {
        ApiSourceProvider(retrofitProvider)
    }

    /*-------------------------------Repository------------------------------------------*/

    private val repositoryProvider: RepositoryProvider by lazy {
        RepositoryProvider(apiSourceProvider, internetSourceProvider, dbProvider)
    }

    /*-------------------------------Room Database------------------------------------------*/

    private val mobiShopDatabase by lazy {
        Room.databaseBuilder(
            provideApplicationContext(),
            MobiShopDatabase::class.java, "mobi-shop-db"
        ).build()
    }

    /*-------------------------------ViewModel Factory------------------------------------------*/

    private val viewModelFactory: ViewModelProvider.Factory by lazy {
        ViewModelFactoryProvider(repositoryProvider)
    }

    /*-------------------------------Public Providers------------------------------------------*/

    private fun provideApplicationContext() = applicationContext

    fun provideViewModelFactory(): ViewModelProvider.Factory = viewModelFactory

}