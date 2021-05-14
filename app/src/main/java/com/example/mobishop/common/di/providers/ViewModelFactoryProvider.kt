package com.example.mobishop.common.di.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobishop.presentation.MainActivityViewModel

class ViewModelFactoryProvider(private val repositoryProvider: RepositoryProvider) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> {
                return MainActivityViewModel(repositoryProvider.provideMobiRepository()) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}