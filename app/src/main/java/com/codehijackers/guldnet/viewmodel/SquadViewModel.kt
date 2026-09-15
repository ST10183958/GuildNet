package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import com.codehijackers.guldnet.model.Squad
import com.codehijackers.guldnet.repository.SquadRepository
import kotlinx.coroutines.flow.StateFlow

class SquadViewModel : ViewModel() {

    private val repository = SquadRepository

    val squads: StateFlow<List<Squad>> =
        repository.squads

    fun joinSquad(
        squadId: String
    ) {
        repository.joinSquad(squadId)
    }

    fun leaveSquad(
        squadId: String
    ) {
        repository.leaveSquad(squadId)
    }

    fun createSquad(
        name: String,
        description: String,
        ownerName: String
    ) {
        repository.createSquad(
            name = name,
            description = description,
            ownerName = ownerName
        )
    }

    fun getSquad(
        squadId: String
    ): Squad? {
        return repository.getSquad(squadId)
    }
}