package com.codehijackers.guldnet.ui.screens.clans

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.screens.clans.components.ClanResponseCard
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors
import com.codehijackers.guldnet.viewmodel.ClanResponseViewModel
import com.codehijackers.guldnet.viewmodel.ClanViewModel

@Composable
fun ClanDetailsScreen(
    clanId: String,
    onBackClicked: () -> Unit = {},
    clanViewModel: ClanViewModel = viewModel(),
    responseViewModel: ClanResponseViewModel = viewModel()
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    val clans by clanViewModel.clans.collectAsState()
    val responses by responseViewModel.responses.collectAsState()

    val clan = clans.find {
        it.id == clanId
    }

    var responseText by remember {
        mutableStateOf("")
    }

    if (clan == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .padding(13.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clickable {
                        onBackClicked()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = strings.back,
                    tint = colors.textPrimary
                )

                Spacer(
                    modifier = Modifier.width(9.dp)
                )

                Text(
                    text = strings.back,
                    color = colors.textSecondary,
                    fontSize = 11.sp
                )
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Text(
                text = strings.discussionNotFound,
                color = colors.textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        return
    }

    val clanResponses = responses.filter {
        it.clanId == clanId
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onBackClicked()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = strings.back,
                    tint = colors.textPrimary
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = strings.clansForum,
                    color = colors.textPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = strings.discussion,
                color = colors.textSecondary,
                fontSize = 9.sp
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 13.dp,
                top = 8.dp,
                end = 13.dp,
                bottom = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            item {
                ClanDetailsHeader(
                    title = clan.title,
                    authorName = clan.authorName,
                    upvotes = clan.upvotes,
                    responseCount = clanResponses.size,
                    categoryText = strings.categoryStrategy
                )

                Spacer(
                    modifier = Modifier.height(13.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(colors.surface)
                        .border(
                            width = 1.dp,
                            color = colors.border,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(15.dp)
                ) {
                    Text(
                        text = clan.content,
                        color = colors.textPrimary,
                        fontSize = 12.sp,
                        lineHeight = 19.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(21.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(3.dp)
                            .height(15.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(colors.primary)
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Text(
                        text = strings.responses,
                        color = colors.textPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.9.sp
                    )
                }
            }

            items(
                items = clanResponses,
                key = {
                    "response_${it.id}"
                }
            ) { response ->
                ClanResponseCard(
                    response = response
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = strings.addResponse,
                    color = colors.textSecondary,
                    fontSize = 9.sp,
                    letterSpacing = 0.9.sp,
                    modifier = Modifier.padding(
                        start = 3.dp,
                        bottom = 7.dp
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(13.dp))
                        .background(colors.surface)
                        .border(
                            width = 1.dp,
                            color = colors.border,
                            shape = RoundedCornerShape(13.dp)
                        )
                        .padding(
                            horizontal = 13.dp,
                            vertical = 11.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = responseText,
                        onValueChange = {
                            responseText = it
                        },
                        modifier = Modifier.weight(1f),
                        singleLine = false,
                        maxLines = 4,
                        textStyle = TextStyle(
                            color = colors.textPrimary,
                            fontSize = 11.sp
                        ),
                        decorationBox = { innerTextField ->
                            if (responseText.isEmpty()) {
                                Text(
                                    text = strings.joinDiscussion,
                                    color = colors.textSecondary,
                                    fontSize = 11.sp
                                )
                            }

                            innerTextField()
                        }
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (responseText.isNotBlank()) {
                                    colors.primary
                                } else {
                                    colors.surfaceVariant
                                }
                            )
                            .clickable(
                                enabled = responseText.isNotBlank()
                            ) {
                                responseViewModel.addResponse(
                                    clanId = clanId,
                                    content = responseText.trim(),
                                    authorName = "You"
                                )

                                responseText = ""
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Send,
                            contentDescription = strings.postResponse,
                            tint = if (responseText.isNotBlank()) {
                                Color.White
                            } else {
                                colors.textSecondary
                            },
                            modifier = Modifier.size(17.dp)
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(25.dp)
                )
            }
        }
    }
}

@Composable
private fun ClanDetailsHeader(
    title: String,
    authorName: String,
    upvotes: Int,
    responseCount: Int,
    categoryText: String
) {
    val colors = currentGuildnetThemeColors

    Column {
        ClanTag(
            text = categoryText
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = title,
            color = colors.textPrimary,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(7.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(23.dp)
                    .clip(CircleShape)
                    .background(colors.selectedBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = authorName
                        .take(1)
                        .uppercase(),
                    color = colors.primary,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.width(7.dp)
            )

            Text(
                text = authorName,
                color = colors.textSecondary,
                fontSize = 9.sp
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Text(
                text = "▲ $upvotes",
                color = colors.textSecondary,
                fontSize = 9.sp
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "💬 $responseCount",
                color = colors.textSecondary,
                fontSize = 9.sp
            )
        }
    }
}

@Composable
private fun ClanTag(
    text: String
) {
    val colors = currentGuildnetThemeColors

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(
                colors.primary.copy(alpha = 0.12f)
            )
            .border(
                width = 1.dp,
                color = colors.primary.copy(alpha = 0.3f),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = text,
            color = colors.primary,
            fontSize = 8.sp,
            fontWeight = FontWeight.Medium
        )
    }
}