package com.majinnaibu.monstercards.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.majinnaibu.monstercards.models.Skill
import java.util.*

object SetOfSkillConverter {
    @JvmStatic
    @TypeConverter
    fun fromSetOfSkill(skills: Set<Skill?>?): String {
        val gson = Gson()
        return gson.toJson(skills)
    }

    @JvmStatic
    @TypeConverter
    fun setOfSkillFromString(string: String?): Set<Skill> {
        val gson = Gson()
        val setType = object : TypeToken<HashSet<Skill?>?>() {}.type
        return gson.fromJson(string, setType)
    }
}