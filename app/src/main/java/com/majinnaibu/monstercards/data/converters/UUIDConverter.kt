package com.majinnaibu.monstercards.data.converters

import androidx.room.TypeConverter
import java.util.*

object UUIDConverter {
    @JvmStatic
    @TypeConverter
    fun fromUUID(uuid: UUID): String {
        return uuid.toString()
    }

    @JvmStatic
    @TypeConverter
    fun uuidFromString(string: String?): UUID {
        return UUID.fromString(string)
    }
}