package com.codehijackers.guldnet.ui.navigation


sealed class GuildnetRoutes(
    val route: String
) {

    data object Login : GuildnetRoutes("login")

    data object Home : GuildnetRoutes("home")
}