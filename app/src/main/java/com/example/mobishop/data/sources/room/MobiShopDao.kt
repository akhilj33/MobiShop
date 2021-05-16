package com.example.mobishop.data.sources.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MobiShopDao {

    /*------------------------------------------Features-----------------------------------------*/

    @Query("SELECT * FROM features")
    suspend fun getFeatures(): List<MobiRoomFeatures>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFeatures(roomItemList: List<MobiRoomFeatures>)

    @Query("DELETE FROM features")
    fun deleteFeatures()

    /*--------------------------------------------Exclusions-----------------------------------------*/

    @Query("SELECT * FROM exclusions")
    suspend fun getExclusions(): List<MobiRoomExclusions>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveExclusions(roomList: List<MobiRoomExclusions>)

    @Query("DELETE FROM exclusions")
    fun deleteExclusions()
}