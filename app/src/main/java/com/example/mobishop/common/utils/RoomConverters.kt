package com.example.mobishop.common.utils

import androidx.room.TypeConverter
import com.example.mobishop.common.utils.JsonUtils.convertJsonStringToObject
import com.example.mobishop.data.sources.room.Option

object RoomConverters {

    @TypeConverter
    @JvmStatic
    fun fromOption(data: List<Option>?): String? {
        return JsonUtils.convertToJsonString(data)
    }

    @TypeConverter
    @JvmStatic
    fun toOption(optionString: String?): List<Option>? {
        return convertJsonStringToObject<List<Option>>(optionString)
    }
}