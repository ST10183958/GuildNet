package com.codehijackers.guldnet.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.codehijackers.guldnet.viewmodel.AppViewModel

@Composable
fun GuildnetApp(
    viewModel: AppViewModel
) {
    val isAuthenticated by viewModel
        .isUserAuthenticated
        .collectAsState()

    if (isAuthenticated) {
        GuildnetAuthenticatedApp()
    }
}