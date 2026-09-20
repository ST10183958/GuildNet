package com.codehijackers.guldnet.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.foundation.clickable
data class GuildnetNavigationItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

private val navigationItems = listOf(

    GuildnetNavigationItem(
        route = GuildnetRoutes.Home.route,
        label = "Home",
        icon = Icons.Outlined.Home
    ),

    GuildnetNavigationItem(
        route = GuildnetRoutes.Squads.route,
        label = "Squads",
        icon = Icons.Outlined.Group
    ),

    GuildnetNavigationItem(
        route = GuildnetRoutes.LoreVault.route,
        label = "LoreVault",
        icon = Icons.Outlined.MenuBook
    ),

    GuildnetNavigationItem(
        route = GuildnetRoutes.Clans.route,
        label = "Clans",
        icon = Icons.Outlined.ChatBubbleOutline
    ),

    GuildnetNavigationItem(
        route = GuildnetRoutes.Profile.route,
        label = "Profile",
        icon = Icons.Outlined.Person
    )
)

@Composable
fun GuildnetBottomBar(
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(
                Color(0xFF080C16)
            )
            .border(
                width = 1.dp,
                color = Color(0xFF202A3D),
                shape = RoundedCornerShape(0.dp)
            )
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        navigationItems.forEach { item ->

            val selected = currentRoute == item.route

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(68.dp)
                    .clickable {
                        navController.navigate(item.route) {

                            popUpTo(
                                GuildnetRoutes.Home.route
                            ) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    .padding(top = 7.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Icon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    modifier = Modifier
                        .height(21.dp),
                    tint = if (selected) {
                        Color(0xFF9B5CFF)
                    } else {
                        Color(0xFF52617D)
                    }
                )

                Text(
                    text = item.label,
                    color = if (selected) {
                        Color(0xFF9B5CFF)
                    } else {
                        Color(0xFF52617D)
                    },
                    fontSize = 9.sp,
                    fontWeight = if (selected) {
                        FontWeight.Medium
                    } else {
                        FontWeight.Normal
                    },
                    modifier = Modifier.padding(top = 3.dp)
                )

                if (selected) {

                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .height(2.dp)
                            .fillMaxWidth(0.35f)
                            .background(
                                Color(0xFF9B5CFF)
                            )
                    )
                }
            }
        }
    }
}