package com.example.mobishop.data.repository

import com.example.mobishop.data.sources.MobiShopSource
import com.example.mobishop.data.response.MobiShopResponse
import com.example.mobishop.data.sources.internet.InternetSource
import com.example.mobishop.data.sources.room.MobiShopDb
import com.example.mobishop.data.sources.room.MobiShopDbImpl
import com.example.mobishop.domain.AppResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  Fetches data from API if internet is present or from Room database if Internet is not present
 **/
interface MobiRepository {
    suspend fun getMobiData(): AppResult<MobiShopResponse>
}

class MobiRepositoryImpl(
    private val mobiShopSource: MobiShopSource,
    private val internetSource: InternetSource,
    private val mobiShopDb: MobiShopDb,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : MobiRepository {

    // If internet is present then fetches data from api and save that data to room db
    // If internet is not present, returning data from room db
    override suspend fun getMobiData(): AppResult<MobiShopResponse> {
        return withContext(dispatcher) {
            if(internetSource.hasInternetConnected()){
                mobiShopSource.getMobiData().also {
                   if(it is AppResult.Success) mobiShopDb.saveMobiShopResponse(it.data)
                }
            }
            else mobiShopDb.getMobiShopResponse()
        }
    }
}