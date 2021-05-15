package com.example.mobishop.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mobishop.R
import com.example.mobishop.common.di.DependencyProvider

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels { DependencyProvider.provideViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpObservers()
        viewModel.getMobiShopData()
    }

    private fun setUpObservers() {
        viewModel.mobiResultLiveData.observe(this , {
            val result = it
        })

        viewModel.errorLiveData.observe(this , {
            if(it)
                Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT).show()
        })
    }
}