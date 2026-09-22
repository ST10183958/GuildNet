package com.codehijackers.guldnet.ui.screens.squads

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors
import com.codehijackers.guldnet.viewmodel.SquadViewModel

@Composable
fun SquadDetailsScreen(
    squadId: String,
    onChatClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    squadViewModel: SquadViewModel = viewModel()
) {
    val colors = currentGuildnetThemeColors
    val squads by squadViewModel.squads.collectAsState()
    val strings = currentGuildnetStrings

    val squad = squads.find {
        it.id == squadId
    }

    if (squad == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(13.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
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
                text = strings.squadNotFound,
                color = colors.textPrimary,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(horizontal = 13.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 14.dp
                ),
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
                    tint = colors.textPrimary,
                    modifier = Modifier.size(21.dp)
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = strings.squads,
                    color = colors.textPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(17.dp))
                .background(colors.surface)
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = RoundedCornerShape(17.dp)
                )
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(colors.selectedBackground)
                        .border(
                            width = 1.dp,
                            color = colors.primary.copy(alpha = 0.45f),
                            shape = RoundedCornerShape(14.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = squad.name
                            .take(3)
                            .uppercase(),
                        color = colors.primary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.width(13.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = squad.name,
                        color = colors.textPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF35D98A))
                        )

                        Spacer(
                            modifier = Modifier.width(5.dp)
                        )

                        Text(
                            text = strings.communityActive,
                            color = Color(0xFF35D98A),
                            fontSize = 9.sp
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            Text(
                text = squad.description,
                color = colors.textSecondary,
                fontSize = 11.sp,
                lineHeight = 17.sp
            )

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SquadStat(
                    value = squad.memberCount.toString(),
                    label = strings.members,
                    modifier = Modifier.weight(1f)
                )

                SquadStat(
                    value = if (squad.isJoined) {
                        strings.joined
                    } else {
                        strings.open
                    },
                    label = strings.status,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Text(
            text = strings.squadChat.uppercase(),
            color = colors.textPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.9.sp
        )

        Spacer(
            modifier = Modifier.height(9.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(colors.surface)
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(11.dp))
                        .background(colors.selectedBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ChatBubbleOutline,
                        contentDescription = strings.squadChat,
                        tint = colors.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.width(11.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = strings.squadChat,
                        color = colors.textPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = if (squad.isJoined) {
                            strings.chatWithSquadMembers
                        } else {
                            strings.joinSquadToEnterChat
                        },
                        color = colors.textSecondary,
                        fontSize = 9.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(13.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(43.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(
                        if (squad.isJoined) {
                            colors.primary
                        } else {
                            colors.surfaceVariant
                        }
                    )
                    .border(
                        width = 1.dp,
                        color = if (squad.isJoined) {
                            colors.primary
                        } else {
                            colors.border
                        },
                        shape = RoundedCornerShape(11.dp)
                    )
                    .clickable(
                        enabled = squad.isJoined
                    ) {
                        onChatClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ChatBubbleOutline,
                        contentDescription = strings.openChat,
                        tint = if (squad.isJoined) {
                            Color.White
                        } else {
                            colors.textSecondary
                        },
                        modifier = Modifier.size(17.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(7.dp)
                    )

                    Text(
                        text = if (squad.isJoined) {
                            strings.openChat
                        } else {
                            strings.joinSquadToChat
                        },
                        color = if (squad.isJoined) {
                            Color.White
                        } else {
                            colors.textSecondary
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Text(
            text = strings.community.uppercase(),
            color = colors.textPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.9.sp
        )

        Spacer(
            modifier = Modifier.height(9.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(colors.surface)
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Groups,
                    contentDescription = strings.community,
                    tint = colors.primary,
                    modifier = Modifier.size(21.dp)
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Column {
                    Text(
                        text = "${squad.memberCount} ${strings.members}",
                        color = colors.textPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = strings.gamingCommunity,
                        color = colors.textSecondary,
                        fontSize = 9.sp
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp)
                .clip(RoundedCornerShape(11.dp))
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = RoundedCornerShape(11.dp)
                )
                .clickable {
                    onBackClicked()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = strings.backToSquads,
                color = colors.textSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(
            modifier = Modifier.height(15.dp)
        )
    }
}

@Composable
private fun SquadStat(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    val colors = currentGuildnetThemeColors

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(11.dp))
            .background(colors.surfaceVariant)
            .border(
                width = 1.dp,
                color = colors.border,
                shape = RoundedCornerShape(11.dp)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 9.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = colors.textPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(3.dp)
        )

        Text(
            text = label,
            color = colors.textSecondary,
            fontSize = 8.sp
        )
    }
}