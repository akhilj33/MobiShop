package com.example.mobishop.common.di.providers

import com.example.mobishop.data.MobiShopApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitProvider {

    private val baseURL: String =
        "https://my-json-server.typicode.com/mhrpatel12/esper-assignment/db/"

    private val gson: Gson = GsonBuilder().setLenient().create()

    private val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)

    private val retrofit: Retrofit =
        Retrofit.Builder().addConverterFactory(gsonConverterFactory)
            .baseUrl(baseURL).build()

    fun provideApiService(): MobiShopApiService = retrofit.create(MobiShopApiService::class.java)


}