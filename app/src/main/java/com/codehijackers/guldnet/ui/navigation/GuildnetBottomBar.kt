package com.codehijackers.guldnet.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue

data class GuildnetNavigationItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

private val navigationItems = listOf(
    GuildnetNavigationItem(
        route = GuildnetRoutes.Home.route,
        label = "Home",
        icon = Icons.Default.Home
    ),
    GuildnetNavigationItem(
        route = GuildnetRoutes.Communities.route,
        label = "Communities",
        icon = Icons.Default.Group
    ),
    GuildnetNavigationItem(
        route = GuildnetRoutes.Search.route,
        label = "Search",
        icon = Icons.Default.Search
    ),
    GuildnetNavigationItem(
        route = GuildnetRoutes.Profile.route,
        label = "Profile",
        icon = Icons.Default.Person
    )
)

@Composable
fun GuildnetBottomBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        navigationItems.forEach { item ->

            NavigationBarItem(
                selected = currentRoute == item.route,

                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(GuildnetRoutes.Home.route) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },

                label = {
                    Text(item.label)
                }
            )
        }
    }
}