package com.codehijackers.guldnet.ui.navigation

sealed class GuildnetRoutes(
    val route: String
) {

    // Authentication
    data object Login : GuildnetRoutes("login")

    // Main navigation
    data object Home : GuildnetRoutes("home")
    data object Guilds : GuildnetRoutes("guilds")
    data object Squads : GuildnetRoutes("squads")
    data object Search : GuildnetRoutes("search")
    data object Profile : GuildnetRoutes("profile")

    // Guilds
    data object GuildDetails :
        GuildnetRoutes("guild/{guildId}") {

        fun createRoute(guildId: String): String {
            return "guild/$guildId"
        }
    }

    data object CreateGuild :
        GuildnetRoutes("guild/create")

    // Squads
    data object SquadDetails :
        GuildnetRoutes("squad/{squadId}") {

        fun createRoute(squadId: String): String {
            return "squad/$squadId"
        }
    }

    data object Chat :
        GuildnetRoutes("squad/{squadId}/chat") {

        fun createRoute(squadId: String): String {
            return "squad/$squadId/chat"
        }
    }

    // Clans
    data object Clans :
        GuildnetRoutes("guild/{guildId}/clans") {

        fun createRoute(guildId: String): String {
            return "guild/$guildId/clans"
        }
    }

    data object CreateClan :
        GuildnetRoutes("guild/{guildId}/clans/create") {

        fun createRoute(guildId: String): String {
            return "guild/$guildId/clans/create"
        }
    }

    data object ClanDetails :
        GuildnetRoutes("clan/{clanId}") {

        fun createRoute(clanId: String): String {
            return "clan/$clanId"
        }
    }

    // LoreVault
    data object LoreVault :
        GuildnetRoutes("lorevault")

    data object GuideDetails :
        GuildnetRoutes("guide/{guideId}") {

        fun createRoute(guideId: String): String {
            return "guide/$guideId"
        }
    }

    data object CreateGuide :
        GuildnetRoutes("guide/create")

    // Profile / settings
    data object Settings :
        GuildnetRoutes("settings")

    // Notifications
    data object Notifications :
        GuildnetRoutes("notifications")

    data object GuildPosts :
        GuildnetRoutes("guild/{guildId}/posts") {

        fun createRoute(guildId: String): String {
            return "guild/$guildId/posts"
        }
    }

    data object CreatePost :
        GuildnetRoutes("guild/{guildId}/posts/create") {

        fun createRoute(guildId: String): String {
            return "guild/$guildId/posts/create"
        }
    }

    data object PostDetails :
        GuildnetRoutes("post/{postId}") {

        fun createRoute(postId: String): String {
            return "post/$postId"
        }
    }


}