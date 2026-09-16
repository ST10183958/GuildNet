package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.model.Guild
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object GuildRepository {

    private val _guilds = MutableStateFlow(
        listOf(
            Guild(
                id = "1",
                name = "Minecraft",
                game = "Minecraft",
                description = "A community for Minecraft players.",
                memberCount = 1250,
                isJoined = true
            ),
            Guild(
                id = "2",
                name = "Elden Ring",
                game = "Elden Ring",
                description = "Discuss builds, bosses and the Lands Between.",
                memberCount = 890
            ),
            Guild(
                id = "3",
                name = "Stardew Valley",
                game = "Stardew Valley",
                description = "A relaxing community for farmers and adventurers.",
                memberCount = 640
            )
        )
    )

    val guilds: StateFlow<List<Guild>> =
        _guilds.asStateFlow()

    fun joinGuild(guildId: String) {
        _guilds.value = _guilds.value.map { guild ->
            if (guild.id == guildId && !guild.isJoined) {
                guild.copy(
                    isJoined = true,
                    memberCount = guild.memberCount + 1
                )
            } else {
                guild
            }
        }
    }

    fun leaveGuild(guildId: String) {
        _guilds.value = _guilds.value.map { guild ->
            if (guild.id == guildId && guild.isJoined) {
                guild.copy(
                    isJoined = false,
                    memberCount = (guild.memberCount - 1).coerceAtLeast(0)
                )
            } else {
                guild
            }
        }
    }

    fun createGuild(
        name: String,
        game: String,
        description: String
    ) {
        val guild = Guild(
            id = (_guilds.value.size + 1).toString(),
            name = name,
            game = game,
            description = description,
            memberCount = 1,
            isJoined = true
        )

        _guilds.value = _guilds.value + guild
    }

    fun getGuild(guildId: String): Guild? {
        return _guilds.value.find {
            it.id == guildId
        }
    }
}