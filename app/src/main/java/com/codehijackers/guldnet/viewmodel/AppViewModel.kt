package com.codehijackers.guldnet.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codehijackers.guldnet.data.local.GuildnetDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val database: GuildnetDatabase
) : ViewModel() {

    companion object {
        private const val TAG = "Guildnet.AppViewModel"
    }

    private val _isUserAuthenticated = MutableStateFlow(false)

    val isUserAuthenticated: StateFlow<Boolean> =
        _isUserAuthenticated.asStateFlow()

    init {
        Log.d(TAG, "AppViewModel created")

        viewModelScope.launch {
            database.userDao()
                .observeLoggedInUser()
                .collect { user ->
                    _isUserAuthenticated.value = user != null

                    Log.d(
                        TAG,
                        "Authenticated user: ${user?.email ?: "none"}"
                    )
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            database.userDao().logoutAllUsers()
        }
    }

    override fun onCleared() {
        Log.d(TAG, "AppViewModel cleared")
        super.onCleared()
    }
}