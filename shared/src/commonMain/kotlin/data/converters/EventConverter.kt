package com.futapp.data

import androidx.room.TypeConverter
import android.util.Log
import androidx.compose.ui.input.key.type
import com.futapp.util.ParsingEventListException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EventConverter {
    private val gson = Gson()
    private val TAG = "EventConverter"

    @TypeConverter
    fun fromEventList(value: List<Event>?): String? {
        Log.d(TAG, "fromEventList: Starting conversion from List<Event> to String")
        if (value == null) {
            Log.w(TAG, "fromEventList: Input list is null, returning null")
            return null
        }
        Log.d(TAG, "fromEventList: Input list: $value")
        val type = object : TypeToken<List<Event>>() {}.type
        val jsonString = gson.toJson(value, type)
        Log.d(TAG, "fromEventList: Result string: $jsonString")
        Log.d(TAG, "fromEventList: Conversion from List<Event> to String completed")
        return jsonString
    }

    @TypeConverter
    fun toEventList(value: String?): List<Event> {
        Log.d(TAG, "toEventList: Starting conversion from String to List<Event>")
        if (value == null) {
            Log.w(TAG, "toEventList: Input string is null, returning empty list")
            return emptyList()
        }
        Log.d(TAG, "toEventList: Input string: $value")
        val type = object : TypeToken<List<Event>>() {}.type
        try {
            val eventList = gson.fromJson<List<Event>>(value, type)
            Log.d(TAG, "toEventList: Result list: $eventList")
            Log.d(TAG, "toEventList: Conversion from String to List<Event> completed")
            return eventList
        } catch (e: Exception) {
            Log.e(TAG, "toEventList: Error parsing event list string: $value", e)
            throw ParsingEventListException(value)
        }
    }
}