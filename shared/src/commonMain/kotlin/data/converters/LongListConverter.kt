package com.futapp.data

import androidx.room.TypeConverter
import android.util.Log

class LongListConverter {
    @TypeConverter
    fun fromLongList(value: List<Long>): String {
        Log.d("LongListConverter", "fromLongList: Input list: $value")
        val result = value.joinToString(separator = ",")
        Log.d("LongListConverter", "fromLongList: Result string: $result")
        return result
    }

    @TypeConverter
    fun toLongList(value: String): List<Long> {
        Log.d("LongListConverter", "toLongList: Input string: $value")
        if (value.isEmpty()) {
            Log.d("LongListConverter", "toLongList: Input string is empty, returning empty list")
            return emptyList()
        }
        val result = value.split(",")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
        Log.d("LongListConverter", "toLongList: Result list: $result")
        return result
    }
}