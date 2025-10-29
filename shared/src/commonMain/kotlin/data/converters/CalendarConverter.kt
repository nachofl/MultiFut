package com.futapp.data

import androidx.room.TypeConverter
import android.util.Log
import androidx.compose.ui.input.key.type
import com.futapp.util.ParsingCalendarException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CalendarConverter {
    private val gson = Gson()
    private val TAG = "CalendarConverter"

    @TypeConverter
    fun fromCalendar(calendar: List<List<Match>>?): String? {
        Log.d(TAG, "fromCalendar: Starting conversion from List<List<Match>> to String")
        if (calendar == null) {
            Log.w(TAG, "fromCalendar: Input calendar is null, returning null")
            return null
        }
        Log.d(TAG, "fromCalendar: Input calendar: $calendar")
        val type = object : TypeToken<List<List<Match>>>() {}.type
        val jsonString = gson.toJson(calendar, type)
        Log.d(TAG, "fromCalendar: Result string: $jsonString")
        Log.d(TAG, "fromCalendar: Conversion from List<List<Match>> to String completed")
        return jsonString
    }

    @TypeConverter
    fun toCalendar(calendarString: String?): List<List<Match>>? {
        Log.d(TAG, "toCalendar: Starting conversion from String to List<List<Match>>")
        if (calendarString == null) {
            Log.w(TAG, "toCalendar: Input string is null, returning null")
            return null
        }
        Log.d(TAG, "toCalendar: Input string: $calendarString")
        val type = object : TypeToken<List<List<Match>>>() {}.type
        try {
            val calendar = gson.fromJson<List<List<Match>>>(calendarString, type)
            Log.d(TAG, "toCalendar: Result calendar: $calendar")
            Log.d(TAG, "toCalendar: Conversion from String to List<List<Match>> completed")
            return calendar
        } catch (e: Exception) {
            Log.e(TAG, "toCalendar: Error parsing calendar string: $calendarString", e)
            throw ParsingCalendarException(calendarString)
        }
    }
}