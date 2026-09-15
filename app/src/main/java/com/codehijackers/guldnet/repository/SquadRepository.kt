package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.model.Squad
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SquadRepository {

    private val _squads = MutableStateFlow(
        listOf(
            Squad(
                id = "1",
                name = "Minecraft Builders",
                description = "A squad for builders and creative players.",
                ownerName = "Alex",
                memberCount = 18,
                isJoined = true
            ),
            Squad(
                id = "2",
                name = "Elden Ring Co-op",
                description = "Find people to explore the Lands Between with.",
                ownerName = "Jordan",
                memberCount = 12
            ),
            Squad(
                id = "3",
                name = "Late Night Gamers",
                description = "A casual squad for gaming sessions.",
                ownerName = "Sam",
                memberCount = 24
            )
        )
    )

    val squads: StateFlow<List<Squad>> =
        _squads.asStateFlow()

    fun joinSquad(
        squadId: String
    ) {
        _squads.value = _squads.value.map { squad ->

            if (squad.id == squadId && !squad.isJoined) {
                squad.copy(
                    isJoined = true,
                    memberCount = squad.memberCount + 1
                )
            } else {
                squad
            }
        }
    }

    fun leaveSquad(
        squadId: String
    ) {
        _squads.value = _squads.value.map { squad ->

            if (squad.id == squadId && squad.isJoined) {
                squad.copy(
                    isJoined = false,
                    memberCount = (squad.memberCount - 1)
                        .coerceAtLeast(0)
                )
            } else {
                squad
            }
        }
    }

    fun createSquad(
        name: String,
        description: String,
        ownerName: String
    ) {
        val squad = Squad(
            id = (_squads.value.size + 1).toString(),
            name = name,
            description = description,
            ownerName = ownerName,
            memberCount = 1,
            isJoined = true
        )

        _squads.value = _squads.value + squad
    }

    fun getSquad(
        squadId: String
    ): Squad? {
        return _squads.value.find {
            it.id == squadId
        }
    }
}