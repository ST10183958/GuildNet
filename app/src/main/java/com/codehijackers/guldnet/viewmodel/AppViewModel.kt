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

    private val _isUserAuthenticated =
        MutableStateFlow(false)

    val isUserAuthenticated: StateFlow<Boolean> =
        _isUserAuthenticated.asStateFlow()

    private val _rememberedUserId =
        MutableStateFlow<Long?>(null)

    val rememberedUserId: StateFlow<Long?> =
        _rememberedUserId.asStateFlow()

    init {
        Log.d(TAG, "AppViewModel created")

        viewModelScope.launch {
            database.userDao()
                .observeLoggedInUser()
                .collect { user ->
                    _rememberedUserId.value = user?.id

                    Log.d(
                        TAG,
                        "Remembered user: ${user?.email ?: "none"}"
                    )
                }
        }
    }

    fun authenticateUser(userId: Long) {
        viewModelScope.launch {
            database.userDao().logoutAllUsers()
            database.userDao().loginUser(userId)

            _isUserAuthenticated.value = true
        }
    }

    fun logout() {
        viewModelScope.launch {
            database.userDao().logoutAllUsers()
            _isUserAuthenticated.value = false
            _rememberedUserId.value = null
        }
    }

    fun lockApp() {
        _isUserAuthenticated.value = false
    }

    override fun onCleared() {
        Log.d(TAG, "AppViewModel cleared")
        super.onCleared()
    }
}