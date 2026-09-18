package com.codehijackers.guldnet.ui.screens.lorevault

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.screens.lorevault.components.GuideCard
import com.codehijackers.guldnet.viewmodel.GuideViewModel

@Composable
fun LoreVaultScreen(
    guildId: String,
    onGuideClicked: (String) -> Unit = {},
    onCreateGuideClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    guideViewModel: GuideViewModel = viewModel()
) {
    val guides by guideViewModel.guides.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    // Only use guides belonging to the currently selected Guild.
    val guildGuides = guides.filter { guide ->
        guide.guildId == guildId
    }

    // Build category filters dynamically from the real guide data.
    val categories = listOf("All") +
            guildGuides
                .map { guide -> guide.category }
                .distinct()

    // Apply both category filtering and search.
    val filteredGuides = guildGuides
        .filter { guide ->
            selectedCategory == "All" ||
                    guide.category == selectedCategory
        }
        .filter { guide ->
            searchQuery.isBlank() ||
                    guide.title.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    guide.description.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    guide.category.contains(
                        searchQuery,
                        ignoreCase = true
                    ) ||
                    guide.authorName.contains(
                        searchQuery,
                        ignoreCase = true
                    )
        }

    // Use the most-viewed matching guide as the featured guide.
    val featuredGuides = filteredGuides
        .sortedByDescending { guide -> guide.viewCount }
        .take(1)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClicked
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                Column {
                    Text(
                        text = "LoreVault",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "Guides & Knowledge",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // Search
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newValue ->
                    searchQuery = newValue
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Search guides...")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor =
                        MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor =
                        MaterialTheme.colorScheme.outline.copy(
                            alpha = 0.35f
                        )
                )
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // Category filters
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->

                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = {
                            selectedCategory = category
                        },
                        label = {
                            Text(category)
                        }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (filteredGuides.isEmpty()) {

                // Empty state
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No guides found",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Try another search or category.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Featured section
                    if (featuredGuides.isNotEmpty()) {

                        item {
                            Text(
                                text = "Featured Guides",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }

                        items(
                            items = featuredGuides,
                            key = { guide ->
                                "featured_${guide.id}"
                            }
                        ) { guide ->

                            GuideCard(
                                guide = guide,
                                onGuideClicked = {
                                    onGuideClicked(guide.id)
                                },
                                featured = true
                            )
                        }
                    }

                    // All Guides heading
                    item {
                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text = "All Guides",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    // All matching guides
                    items(
                        items = filteredGuides,
                        key = { guide ->
                            "guide_${guide.id}"
                        }
                    ) { guide ->

                        GuideCard(
                            guide = guide,
                            onGuideClicked = {
                                onGuideClicked(guide.id)
                            }
                        )
                    }

                    // Space so FAB does not cover final card.
                    item {
                        Spacer(
                            modifier = Modifier.height(80.dp)
                        )
                    }
                }
            }
        }

        // Create Guide button
        FloatingActionButton(
            onClick = onCreateGuideClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Create Guide"
            )
        }
    }
}