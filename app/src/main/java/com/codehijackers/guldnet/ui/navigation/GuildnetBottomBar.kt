package com.codehijackers.guldnet.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors

data class GuildnetNavigationItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun GuildnetBottomBar(
    navController: NavHostController
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    val navigationItems = listOf(
        GuildnetNavigationItem(
            route = GuildnetRoutes.Home.route,
            label = strings.home,
            icon = Icons.Outlined.Home
        ),
        GuildnetNavigationItem(
            route = GuildnetRoutes.Squads.route,
            label = strings.squads,
            icon = Icons.Outlined.Group
        ),
        GuildnetNavigationItem(
            route = GuildnetRoutes.LoreVault.route,
            label = strings.loreVault,
            icon = Icons.Outlined.MenuBook
        ),
        GuildnetNavigationItem(
            route = GuildnetRoutes.Clans.route,
            label = strings.clans,
            icon = Icons.Outlined.ChatBubbleOutline
        ),
        GuildnetNavigationItem(
            route = GuildnetRoutes.Profile.route,
            label = strings.profile,
            icon = Icons.Outlined.Person
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(82.dp)
            .background(colors.surface)
            .border(
                width = 1.dp,
                color = colors.border,
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
                    .height(82.dp)
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
                    .padding(
                        top = 8.dp,
                        bottom = 5.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (selected) {
                                colors.selectedBackground
                            } else {
                                androidx.compose.ui.graphics.Color.Transparent
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected) {
                            colors.primary
                        } else {
                            colors.textSecondary
                        }
                    )
                }

                Text(
                    text = item.label,
                    color = if (selected) {
                        colors.primary
                    } else {
                        colors.textSecondary
                    },
                    fontSize = 11.sp,
                    fontWeight = if (selected) {
                        FontWeight.SemiBold
                    } else {
                        FontWeight.Normal
                    },
                    modifier = Modifier.padding(
                        top = 2.dp
                    )
                )

                if (selected) {
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .height(3.dp)
                            .fillMaxWidth(0.28f)
                            .background(
                                colors.primary,
                                RoundedCornerShape(3.dp)
                            )
                    )
                }
            }
        }
    }
}