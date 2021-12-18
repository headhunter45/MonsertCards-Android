package com.majinnaibu.monstercards.data.converters

import androidx.room.TypeConverter
import com.majinnaibu.monstercards.data.enums.ArmorType
import com.majinnaibu.monstercards.data.enums.ArmorType.Companion.valueOfString

object ArmorTypeConverter {
    @JvmStatic
    @TypeConverter
    fun fromArmorType(armorType: ArmorType): String {
        return armorType.stringValue
    }

    @JvmStatic
    @TypeConverter
    fun armorTypeFromStringValue(stringValue: String?): ArmorType {
        return valueOfString(stringValue!!)
    }
}