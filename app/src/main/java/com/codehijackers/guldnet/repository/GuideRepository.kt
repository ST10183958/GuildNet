package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.model.Guide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object GuideRepository {

    private val _guides = MutableStateFlow(
        listOf(
            Guide(
                id = "1",
                guildId = "1",
                title = "Minecraft Beginner Survival Guide",
                description = "Everything you need to know when starting a new survival world.",
                content = """
                    Start by collecting wood and basic resources.

                    Build a simple shelter before nightfall.

                    Create basic tools and obtain food.

                    Once you are established, begin exploring caves
                    and collecting iron, coal and other resources.

                    From there you can start developing farms,
                    enchanting equipment and preparing for the Nether.
                """.trimIndent(),
                category = "Beginner",
                authorName = "Alex",
                createdAt = "Today",
                viewCount = 124
            ),

            Guide(
                id = "2",
                guildId = "1",
                title = "How to Build an Iron Farm",
                description = "A practical guide to creating an efficient iron farm.",
                content = """
                    Start by selecting a safe location away from
                    existing villages.

                    Construct the villager area according to the
                    farm design.

                    Add the zombie containment area.

                    Finally, create the collection system beneath
                    the spawning platform.
                """.trimIndent(),
                category = "Tutorial",
                authorName = "Jordan",
                createdAt = "Yesterday",
                viewCount = 87
            ),

            Guide(
                id = "3",
                guildId = "2",
                title = "Elden Ring Early Game Builds",
                description = "A collection of useful builds for starting a new character.",
                content = """
                    Focus on choosing a build that matches your
                    preferred playstyle.

                    Strength builds benefit from heavy weapons.

                    Dexterity builds provide faster attacks.

                    Intelligence and Faith builds provide access
                    to powerful magical abilities.
                """.trimIndent(),
                category = "Builds",
                authorName = "Sam",
                createdAt = "2 days ago",
                viewCount = 203
            )
        )
    )

    val guides: StateFlow<List<Guide>> =
        _guides.asStateFlow()

    fun getGuidesForGuild(
        guildId: String
    ): List<Guide> {
        return _guides.value.filter {
            it.guildId == guildId
        }
    }

    fun getGuide(
        guideId: String
    ): Guide? {
        return _guides.value.find {
            it.id == guideId
        }
    }

    fun createGuide(
        guildId: String,
        title: String,
        description: String,
        content: String,
        category: String,
        authorName: String
    ) {
        val guide = Guide(
            id = (_guides.value.size + 1).toString(),
            guildId = guildId,
            title = title,
            description = description,
            content = content,
            category = category,
            authorName = authorName,
            createdAt = "Just now"
        )

        _guides.value = _guides.value + guide
    }
}