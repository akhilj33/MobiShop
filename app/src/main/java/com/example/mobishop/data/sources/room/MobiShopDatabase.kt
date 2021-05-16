package com.example.mobishop.data.sources.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobishop.common.utils.RoomConverters

@Database(
    entities = [MobiRoomFeatures::class, MobiRoomExclusions::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class MobiShopDatabase : RoomDatabase() {
    abstract fun mobiShopDao(): MobiShopDao
}