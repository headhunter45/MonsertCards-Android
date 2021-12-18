package com.majinnaibu.monstercards.data.enums

enum class ProficiencyType(
    @JvmField val stringValue: String,
    @JvmField val displayName: String,
    @JvmField val label: String
) {
    NONE("none", "None", ""),
    PROFICIENT("proficient", "Proficient", "P"),
    EXPERTISE("expertise", "Expertise", "Ex");

    companion object {
        @JvmStatic
        fun valueOfString(string: String): ProficiencyType {
            for (proficiencyType in values()) {
                if (proficiencyType.stringValue == string) {
                    return proficiencyType
                }
            }
            return NONE
        }
    }
}