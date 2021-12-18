package com.majinnaibu.monstercards.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

object SetOfStringConverter {
    @JvmStatic
    @TypeConverter
    fun fromSetOfString(strings: Set<String?>?): String {
        val gson = Gson()
        return gson.toJson(strings)
    }

    @JvmStatic
    @TypeConverter
    fun setOfStringFromString(string: String?): Set<String> {
        val gson = Gson()
        val setType = object : TypeToken<HashSet<String?>?>() {}.type
        return gson.fromJson(string, setType)
    }
}