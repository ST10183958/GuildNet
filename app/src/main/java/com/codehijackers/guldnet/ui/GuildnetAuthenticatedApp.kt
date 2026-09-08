package com.codehijackers.guldnet.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.codehijackers.guldnet.ui.navigation.GuildnetBottomBar
import com.codehijackers.guldnet.ui.navigation.GuildnetNavHost
import com.codehijackers.guldnet.ui.navigation.GuildnetRoutes

@Composable
fun GuildnetAuthenticatedApp() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            GuildnetBottomBar(
                navController = navController
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            GuildnetNavHost(
                navController = navController,
                startDestination = GuildnetRoutes.Home.route
            )
        }
    }
}