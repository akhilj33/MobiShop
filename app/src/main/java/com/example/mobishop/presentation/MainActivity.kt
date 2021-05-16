package com.example.mobishop.presentation

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobishop.R
import com.example.mobishop.common.di.DependencyProvider
import com.example.mobishop.databinding.ActivityMainBinding
import com.example.mobishop.databinding.AlertDialogDialogBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels { DependencyProvider.provideViewModelFactory() }
    private val mobiShopAdapter by lazy { MobiShopAdapter(onSubmitClick) }
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpObservers()
        initRecyclerView()
        viewModel.getMobiShopData()
    }

    private fun setUpObservers() {
        viewModel.mobiResultLiveData.observe(this , {
            mobiShopAdapter.addItems(it)
        })

        viewModel.errorLiveData.observe(this , {
            if(it)
                Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        viewBinding.rvMobiShop.apply {
            layoutManager = linearLayoutManager
            adapter = mobiShopAdapter
        }
    }

    private val onSubmitClick = fun(item: MobiShopSelectedItem?) {
        if (item == null)
            Toast.makeText(this, getString(R.string.error_msg_item), Toast.LENGTH_SHORT).show()
        else showDialog(item)
    }

    private fun showDialog(item: MobiShopSelectedItem){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.alert_dialog_dialog, null)
        builder.setView(dialogView)
        dialogView.findViewById<TextView>(R.id.name).text = item.name
        dialogView.findViewById<TextView>(R.id.storage).text = item.storage
        dialogView.findViewById<TextView>(R.id.features).text = item.otherFeatures.toString()
        builder.setPositiveButton(getString(R.string.ok)) { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}