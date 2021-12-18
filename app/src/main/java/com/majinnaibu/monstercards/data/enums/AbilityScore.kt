package com.majinnaibu.monstercards.data.enums

enum class AbilityScore(
    @JvmField val stringValue: String,
    @JvmField val displayName: String,
    @JvmField val shortDisplayName: String
) {
    STRENGTH("strength", "Strength", "STR"),
    DEXTERITY("dexterity", "Dexterity", "DEX"),
    CONSTITUTION("constitution", "Constitution", "CON"),
    INTELLIGENCE("intelligence", "Intelligence", "INT"),
    WISDOM("wisdom", "Wisdom", "WIS"),
    CHARISMA("charisma", "Charisma", "CHA");

    companion object {
        @JvmStatic
        fun valueOfString(string: String): AbilityScore {
            for (abilityScore in values()) {
                if (abilityScore.stringValue == string) {
                    return abilityScore
                }
            }
            return STRENGTH
        }
    }
}