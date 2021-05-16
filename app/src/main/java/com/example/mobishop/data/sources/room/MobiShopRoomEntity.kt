package com.example.mobishop.data.sources.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "features")
data class MobiRoomFeatures(
    @PrimaryKey @ColumnInfo(name = "feature_id") val featureId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "options") val options: List<Option>
)

@Entity(tableName = "exclusions")
data class MobiRoomExclusions(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "feature_id_1") val featureId1: String,
    @ColumnInfo(name = "options_id_1") val optionId1: String,
    @ColumnInfo(name = "feature_id_2") val featureId2: String,
    @ColumnInfo(name = "options_id_2") val optionId2: String
)

data class Option(
    val icon: String,
    val id: String,
    val name: String
)

