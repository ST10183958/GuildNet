package com.codehijackers.guldnet.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.codehijackers.guldnet.ui.components.GuildnetBackground

import com.codehijackers.guldnet.ui.navigation.GuildnetBottomBar
import com.codehijackers.guldnet.ui.navigation.GuildnetNavHost
import com.codehijackers.guldnet.ui.navigation.GuildnetRoutes

@Composable
fun GuildnetAuthenticatedApp() {

    val navController = rememberNavController()

    GuildnetBackground {

    Scaffold(
        containerColor = Color.Transparent,

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
}
