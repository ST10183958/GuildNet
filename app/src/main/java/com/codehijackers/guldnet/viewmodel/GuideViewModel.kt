package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import com.codehijackers.guldnet.model.Guide
import com.codehijackers.guldnet.repository.GuideRepository
import kotlinx.coroutines.flow.StateFlow

class GuideViewModel : ViewModel() {

    private val repository = GuideRepository

    val guides: StateFlow<List<Guide>> =
        repository.guides

    fun getGuidesForGuild(
        guildId: String
    ): List<Guide> {
        return repository.getGuidesForGuild(guildId)
    }

    fun getGuide(
        guideId: String
    ): Guide? {
        return repository.getGuide(guideId)
    }

    fun createGuide(
        guildId: String,
        title: String,
        description: String,
        content: String,
        category: String,
        authorName: String
    ) {
        repository.createGuide(
            guildId = guildId,
            title = title,
            description = description,
            content = content,
            category = category,
            authorName = authorName
        )
    }
}