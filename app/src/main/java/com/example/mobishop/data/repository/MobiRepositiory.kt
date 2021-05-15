package com.example.mobishop.data.repository

import com.example.mobishop.data.sources.MobiShopSource
import com.example.mobishop.data.response.MobiShopResponse
import com.example.mobishop.domain.AppResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MobiRepository {
    suspend fun getMobiData(): AppResult<MobiShopResponse>
}

class MobiRepositoryImpl(
    private val mobiShopSource: MobiShopSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MobiRepository {

    override suspend fun getMobiData(): AppResult<MobiShopResponse> {
        return withContext(dispatcher) {
            mobiShopSource.getMobiData()
        }
    }
}