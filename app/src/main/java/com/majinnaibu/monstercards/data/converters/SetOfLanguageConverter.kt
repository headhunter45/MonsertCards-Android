package com.majinnaibu.monstercards.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.majinnaibu.monstercards.models.Language
import java.util.*

object SetOfLanguageConverter {
    @JvmStatic
    @TypeConverter
    fun fromSetOfLanguage(languages: Set<Language?>?): String {
        val gson = Gson()
        return gson.toJson(languages)
    }

    @JvmStatic
    @TypeConverter
    fun setOfLanguageFromString(string: String?): Set<Language> {
        val gson = Gson()
        val setType = object : TypeToken<HashSet<Language?>?>() {}.type
        return gson.fromJson(string, setType)
    }
}