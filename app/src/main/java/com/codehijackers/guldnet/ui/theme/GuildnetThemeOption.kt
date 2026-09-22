package com.codehijackers.guldnet.ui.theme

enum class GuildnetThemeOption(
    val key: String,
    val displayName: String
) {
    DARK_PURPLE(
        key = "dark_purple",
        displayName = "Dark Purple"
    ),
    ROYAL_PACIFIC(
        key = "royal_pacific",
        displayName = "Royal Pacific"
    ),
    SABEINS(
        key = "sabeins",
        displayName = "Sabeins"
    ),
    VELVET_BLOOM(
        key = "velvet_bloom",
        displayName = "Velvet Bloom"
    );

    companion object {
        fun fromKey(key: String): GuildnetThemeOption {
            return entries.firstOrNull {
                it.key == key
            } ?: DARK_PURPLE
        }
    }
}