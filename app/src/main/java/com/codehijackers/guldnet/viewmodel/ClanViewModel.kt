package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import com.codehijackers.guldnet.model.Clan
import com.codehijackers.guldnet.repository.ClanRepository
import kotlinx.coroutines.flow.StateFlow

class lanViewModel : ViewModel() {

    private val repository = ClanRepository

    val clans: StateFlow<List<Clan>> =
        repository.clans

    fun getClansForGuild(
        guildId: String
    ): List<Clan> {
        return repository.getClansForGuild(guildId)
    }

    fun createClan(
        guildId: String,
        title: String,
        content: String,
        authorName: String
    ) {
        repository.createClan(
            guildId = guildId,
            title = title,
            content = content,
            authorName = authorName
        )
    }
}