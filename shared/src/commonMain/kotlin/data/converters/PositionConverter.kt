package com.futapp.data

import androidx.room.TypeConverter

class PositionConverter {
    @TypeConverter
    fun fromPosition(position: Position): String {
        return position.name
    }

    @TypeConverter
    fun toPosition(value: String): Position {
        return Position.valueOf(value)
    }
}