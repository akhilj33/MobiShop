package com.example.mobishop.data

import com.example.mobishop.data.response.MobiShopResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface MobiShopApiService {

    @GET
    suspend fun getMobiShopData(
        @Url url: String
    ): Response<MobiShopResponse>
}