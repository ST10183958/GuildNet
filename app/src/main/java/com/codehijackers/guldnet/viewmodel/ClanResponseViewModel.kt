package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import com.codehijackers.guldnet.model.ClanResponse
import com.codehijackers.guldnet.repository.ClanResponseRepository
import kotlinx.coroutines.flow.StateFlow

class ClanResponseViewModel : ViewModel() {

    private val repository = ClanResponseRepository

    val responses: StateFlow<List<ClanResponse>> =
        repository.responses

    fun getResponsesForClan(
        clanId: String
    ): List<ClanResponse> {
        return repository.getResponsesForClan(clanId)
    }

    fun addResponse(
        clanId: String,
        content: String,
        authorName: String
    ) {
        repository.addResponse(
            clanId = clanId,
            authorName = authorName,
            content = content
        )
    }
}