package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ProfileRepository {

    private val _profile = MutableStateFlow(
        UserProfile(
            id = "user-1",
            displayName = "You",
            username = "player",
            bio = "Gaming enthusiast and Guildnet explorer.",
            email = "player@guildnet.local"
        )
    )

    val profile: StateFlow<UserProfile> =
        _profile.asStateFlow()

    fun updateProfile(
        displayName: String,
        username: String,
        bio: String
    ) {
        _profile.value = _profile.value.copy(
            displayName = displayName,
            username = username,
            bio = bio
        )
    }
}