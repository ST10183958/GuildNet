package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import com.codehijackers.guldnet.model.UserProfile
import com.codehijackers.guldnet.repository.ProfileRepository
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    private val repository = ProfileRepository

    val profile: StateFlow<UserProfile> =
        repository.profile

    fun updateProfile(
        displayName: String,
        username: String,
        bio: String
    ) {
        repository.updateProfile(
            displayName = displayName,
            username = username,
            bio = bio
        )
    }
}