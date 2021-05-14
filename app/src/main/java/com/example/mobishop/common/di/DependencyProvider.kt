package com.example.mobishop.common.di

import androidx.lifecycle.ViewModelProvider
import com.example.mobishop.common.di.providers.ApiSourceProvider
import com.example.mobishop.common.di.providers.RepositoryProvider
import com.example.mobishop.common.di.providers.RetrofitProvider
import com.example.mobishop.common.di.providers.ViewModelFactoryProvider

object DependencyProvider {

    private val retrofitProvider: RetrofitProvider by lazy {
        RetrofitProvider()
    }

    private val apiSourceProvider: ApiSourceProvider by lazy {
        ApiSourceProvider(retrofitProvider)
    }

    /*-------------------------------Repository------------------------------------------*/

    private val repositoryProvider: RepositoryProvider by lazy {
        RepositoryProvider(apiSourceProvider)
    }

    private val viewModelFactory: ViewModelProvider.Factory by lazy {
        ViewModelFactoryProvider(repositoryProvider)
    }

    /*-------------------------------Public Providers------------------------------------------*/
    fun provideViewModelFactory(): ViewModelProvider.Factory = viewModelFactory

}