package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.model.ClanResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ClanResponseRepository {

    private val _responses = MutableStateFlow(
        listOf(
            ClanResponse(
                id = "1",
                clanId = "1",
                authorName = "Jordan",
                content = "I normally start with a basic wheat farm and then move to an iron farm.",
                createdAt = "Today"
            ),
            ClanResponse(
                id = "2",
                clanId = "1",
                authorName = "Sam",
                content = "Villager trading halls are also really useful early on.",
                createdAt = "Today"
            ),
            ClanResponse(
                id = "3",
                clanId = "3",
                authorName = "Alex",
                content = "Malenia was brutal but incredibly satisfying to finally beat.",
                createdAt = "Yesterday"
            )
        )
    )

    val responses: StateFlow<List<ClanResponse>> =
        _responses.asStateFlow()

    fun getResponsesForClan(
        clanId: String
    ): List<ClanResponse> {
        return _responses.value.filter {
            it.clanId == clanId
        }
    }

    fun addResponse(
        clanId: String,
        authorName: String,
        content: String
    ) {
        val response = ClanResponse(
            id = (_responses.value.size + 1).toString(),
            clanId = clanId,
            authorName = authorName,
            content = content,
            createdAt = "Just now"
        )

        _responses.value = _responses.value + response
    }
}