package com.example.mobishop.data.sources

import com.example.mobishop.data.MobiShopApiService
import com.example.mobishop.data.sources.api.model.response.MobiShopResponse
import com.example.mobishop.domain.AppError
import com.example.mobishop.domain.AppResult
import retrofit2.Response

interface MobiShopSource {
    suspend fun getMobiData(): AppResult<MobiShopResponse>
}

class MobiShopSourceImpl(private val apiService: MobiShopApiService) : MobiShopSource {

    override suspend fun getMobiData(): AppResult<MobiShopResponse> {
        var response: Response<MobiShopResponse>? = null
        var exception: Exception? = null

        try {
            response =
                apiService.getMobiShopData("https://my-json-server.typicode.com/mhrpatel12/esper-assignment/db")
        } catch (e: Exception) {
            exception = e
        }

        return if (exception != null) {
            AppResult.Failure(AppError("Something Went wrong"))
        } else if (response != null && response.isSuccessful && response.body() != null) {
            AppResult.Success(response.body()!!)
        } else {
            AppResult.Failure(AppError("Something Went wrong"))
        }
    }
}
