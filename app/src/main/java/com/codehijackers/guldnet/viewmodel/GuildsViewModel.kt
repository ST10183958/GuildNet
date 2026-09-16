package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import com.codehijackers.guldnet.model.Guild
import com.codehijackers.guldnet.repository.GuildRepository
import kotlinx.coroutines.flow.StateFlow

class GuildViewModel : ViewModel() {

    private val repository = GuildRepository

    val guilds: StateFlow<List<Guild>> =
        repository.guilds

    fun joinGuild(guildId: String) {
        repository.joinGuild(guildId)
    }

    fun leaveGuild(guildId: String) {
        repository.leaveGuild(guildId)
    }

    fun createGuild(
        name: String,
        game: String,
        description: String
    ) {
        repository.createGuild(
            name = name,
            game = game,
            description = description
        )
    }

    fun getGuild(guildId: String): Guild? {
        return repository.getGuild(guildId)
    }
}