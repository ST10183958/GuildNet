package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codehijackers.guldnet.model.UserProfile
import com.codehijackers.guldnet.repository.ProfileRepository
import com.codehijackers.guldnet.repository.LanguageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileUiState(
    val displayName: String = "",
    val email: String = "",
    val initial: String = "",

    val rank: String = "Gold II",
    val badge: String = "Veteran",
    val matches: String = "128",
    val winRate: String = "68%",
    val squadRank: String = "#12",
    val posts: String = "47",

    val notificationsEnabled: Boolean = true,
    val privacy: String = "Friends",
    val theme: String = "System default",
    val language: String = "English"
)

class ProfileViewModel : ViewModel() {

    private val repository = ProfileRepository

    val profile: StateFlow<UserProfile> =
        repository.profile

    private val _uiState = MutableStateFlow(
        createUiState(repository.profile.value)
    )

    val uiState: StateFlow<ProfileUiState> =
        _uiState.asStateFlow()

    init {

        viewModelScope.launch {
            repository.profile.collect { profile ->
                _uiState.update { current ->
                    current.copy(
                        displayName = profile.displayName,
                        email = profile.email,
                        initial = profile.displayName
                            .firstOrNull()
                            ?.uppercase()
                            ?: "?"
                    )
                }
            }
        }

        viewModelScope.launch {
            LanguageRepository.language.collect { language ->
                _uiState.update { current ->
                    current.copy(
                        language = language.displayName
                    )
                }
            }
        }
    }

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

    fun toggleNotifications() {
        _uiState.update {
            it.copy(
                notificationsEnabled =
                    !it.notificationsEnabled
            )
        }
    }

    private companion object {

        fun createUiState(
            profile: UserProfile
        ): ProfileUiState {
            return ProfileUiState(
                displayName = profile.displayName,
                email = profile.email,
                initial = profile.displayName
                    .firstOrNull()
                    ?.uppercase()
                    ?: "?"
            )
        }
    }
}