package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.model.Clan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ClanRepository {

    private val _clans = MutableStateFlow(
        listOf(
            Clan(
                id = "1",
                guildId = "1",
                title = "Best Minecraft starter farms?",
                content = "What farms do you normally build first when starting a new survival world?",
                authorName = "Alex",
                createdAt = "Today",
                responseCount = 14,
                upvotes = 36
            ),
            Clan(
                id = "2",
                guildId = "1",
                title = "Nether transportation network",
                content = "I'm planning a Nether highway connecting several bases. Any design suggestions?",
                authorName = "Jordan",
                createdAt = "Yesterday",
                responseCount = 9,
                upvotes = 28
            ),
            Clan(
                id = "3",
                guildId = "2",
                title = "Favourite Elden Ring boss?",
                content = "Which boss fight did you enjoy the most?",
                authorName = "Sam",
                createdAt = "2 days ago",
                responseCount = 21,
                upvotes = 47
            )
        )
    )

    val clans: StateFlow<List<Clan>> =
        _clans.asStateFlow()

    fun getClansForGuild(
        guildId: String
    ): List<Clan> {
        return _clans.value.filter {
            it.guildId == guildId
        }
    }

    fun createClan(
        guildId: String,
        title: String,
        content: String,
        authorName: String
    ) {
        val clan = Clan(
            id = (_clans.value.size + 1).toString(),
            guildId = guildId,
            title = title,
            content = content,
            authorName = authorName,
            createdAt = "Just now"
        )

        _clans.value = _clans.value + clan
    }
}