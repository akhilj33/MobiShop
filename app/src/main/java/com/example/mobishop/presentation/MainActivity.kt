package com.example.mobishop.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mobishop.R
import com.example.mobishop.common.di.DependencyProvider

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels { DependencyProvider.provideViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getMobiShopData()
    }
}