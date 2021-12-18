package com.majinnaibu.monstercards.data.enums

enum class ArmorType(
    @JvmField val stringValue: String,
    @JvmField val displayName: String,
    @JvmField val baseArmorClass: Int
) {
    NONE("none", "None", 10),
    NATURAL_ARMOR("natural armor", "Natural Armor", 10),
    MAGE_ARMOR("mage armor", "Mage Armor", 10),
    PADDED("padded", "Padded", 11),
    LEATHER("leather", "Leather", 11),
    STUDDED_LEATHER("studded", "Studded Leather", 12),
    HIDE("hide", "Hide", 12),
    CHAIN_SHIRT("chain shirt", "Chain Shirt", 13),
    SCALE_MAIL("scale mail", "Scale Mail", 14),
    BREASTPLATE("breastplate", "Breastplate", 14),
    HALF_PLATE("half plate", "Half Plate", 15),
    RING_MAIL("ring mail", "Ring Mail", 14),
    CHAIN_MAIL("chain mail", "Chain Mail", 16),
    SPLINT_MAIL("splint", "Splint Mail", 17),
    PLATE_MAIL("plate", "Plate Mail", 18),
    OTHER("other", "Other", 10);

    companion object {
        @JvmStatic
        fun valueOfString(string: String): ArmorType {
            for (armorType in values()) {
                if (armorType.stringValue == string) {
                    return armorType
                }
            }
            return NONE
        }
    }
}