package com.majinnaibu.monstercards.data.enums

enum class AdvantageType(
    val stringValue: String,
    val displayName: String,
    @JvmField val label: String
) {
    NONE("none", "None", ""),
    ADVANTAGE("advantage", "Advantage", "A"),
    DISADVANTAGE("disadvantage", "Disadvantage", "D");

    companion object {
        @JvmStatic
        fun valueOfString(string: String): AdvantageType {
            for (advantageType in values()) {
                if (advantageType.stringValue == string) {
                    return advantageType
                }
            }
            return NONE
        }
    }
}