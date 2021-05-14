package com.example.mobishop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobishop.data.repository.MobiRepository
import com.example.mobishop.domain.AppResult
import kotlinx.coroutines.launch

class MainActivityViewModel(private val mobiRepository: MobiRepository): ViewModel() {

    fun getMobiShopData(){
        viewModelScope.launch {
            when (val result = mobiRepository.getMobiData()){
                is AppResult.Success -> {
                    val a = result.data
                }
                is AppResult.Failure -> {

                }
            }
        }
    }
}