package com.example.mobishop.data.sources

import com.example.mobishop.common.utils.Constants.BASE_URL
import com.example.mobishop.common.utils.Constants.ERROR_CODE
import com.example.mobishop.data.MobiShopApiService
import com.example.mobishop.data.response.MobiShopResponse
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
                    apiService.getMobiShopData(BASE_URL)
        } catch (e: Exception) {
            exception = e
        }

        return if (exception != null) {
            AppResult.Failure(AppError(code = ERROR_CODE))
        } else if (response != null && response.isSuccessful && response.body() != null) {
            AppResult.Success(response.body()!!)
        } else {
            AppResult.Failure(AppError(code = ERROR_CODE))
        }
    }
}
