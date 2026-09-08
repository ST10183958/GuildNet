package com.codehijackers.guldnet.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class CommunityPreview(
    val name: String,
    val memberCount: String,
    val icon: String
)

data class DiscussionPreview(
    val title: String,
    val community: String,
    val responseCount: Int
)

data class GuidePreview(
    val title: String,
    val game: String,
    val readTime: String
)

@Composable
fun HomeScreen() {

    val communities = remember {
        listOf(
            CommunityPreview(
                name = "Minecraft",
                memberCount = "1.2k members",
                icon = "🎮"
            ),
            CommunityPreview(
                name = "Elden Ring",
                memberCount = "840 members",
                icon = "⚔️"
            ),
            CommunityPreview(
                name = "Stardew Valley",
                memberCount = "620 members",
                icon = "🌱"
            )
        )
    }

    val discussions = remember {
        listOf(
            DiscussionPreview(
                title = "What are the best early-game weapons?",
                community = "Elden Ring",
                responseCount = 24
            ),
            DiscussionPreview(
                title = "What should I build first?",
                community = "Minecraft",
                responseCount = 18
            ),
            DiscussionPreview(
                title = "Best crops for year one?",
                community = "Stardew Valley",
                responseCount = 12
            )
        )
    }

    val guides = remember {
        listOf(
            GuidePreview(
                title = "Beginner's Guide",
                game = "Minecraft",
                readTime = "12 min read"
            ),
            GuidePreview(
                title = "Starting Strong",
                game = "Elden Ring",
                readTime = "8 min read"
            )
        )
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        HomeHeader()

        SearchField()

        SectionHeader(
            title = "Your Communities"
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(communities) { community ->
                CommunityCard(
                    community = community
                )
            }
        }

        SectionHeader(
            title = "Recent Discussions"
        )

        discussions.forEach { discussion ->
            DiscussionCard(
                discussion = discussion
            )
        }

        SectionHeader(
            title = "LoreVault"
        )

        guides.forEach { guide ->
            GuideCard(
                guide = guide
            )
        }
    }
}

@Composable
private fun HomeHeader() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(
                text = "Guildnet",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Welcome back, Gamer",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        IconButton(
            onClick = {
                // Notifications will be implemented later.
            }
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications"
            )
        }
    }
}

@Composable
private fun SearchField() {

    OutlinedTextField(
        value = "",
        onValueChange = {
            // Search functionality will be implemented later.
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text("Find communities...")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        singleLine = true
    )
}

@Composable
private fun SectionHeader(
    title: String
) {

    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun CommunityCard(
    community: CommunityPreview
) {

    Card(
        modifier = Modifier.width(170.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = community.icon,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = community.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = community.memberCount,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun DiscussionCard(
    discussion: DiscussionPreview
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = discussion.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "${discussion.community} • " +
                            "${discussion.responseCount} responses",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Open discussion"
            )
        }
    }
}

@Composable
private fun GuideCard(
    guide: GuidePreview
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = guide.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "${guide.game} • ${guide.readTime}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Open guide"
            )
        }
    }
}