package com.majinnaibu.monstercards.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.majinnaibu.monstercards.models.Trait
import java.util.*

object ListOfTraitsConverter {
    @JvmStatic
    @TypeConverter
    fun fromListOfTraits(traits: List<Trait?>?): String {
        val gson = Gson()
        return gson.toJson(traits)
    }

    @JvmStatic
    @TypeConverter
    fun listOfTraitsFromString(string: String?): List<Trait> {
        val gson = Gson()
        val setType = object : TypeToken<ArrayList<Trait?>?>() {}.type
        return gson.fromJson(string, setType)
    }
}