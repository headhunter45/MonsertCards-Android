package com.majinnaibu.monstercards.data.converters

import androidx.room.TypeConverter
import com.majinnaibu.monstercards.data.enums.ChallengeRating
import com.majinnaibu.monstercards.data.enums.ChallengeRating.Companion.valueOfString

object ChallengeRatingConverter {
    @JvmStatic
    @TypeConverter
    fun fromChallengeRating(challengeRating: ChallengeRating): String {
        return challengeRating.stringValue
    }

    @JvmStatic
    @TypeConverter
    fun challengeRatingFromStringValue(stringValue: String?): ChallengeRating {
        return valueOfString(stringValue!!)
    }
}