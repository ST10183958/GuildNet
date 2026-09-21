package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codehijackers.guldnet.data.local.GuildnetDatabase

class AppViewModelFactory(
    private val database: GuildnetDatabase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(database) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}