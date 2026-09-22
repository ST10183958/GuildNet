package com.codehijackers.guldnet.ui.screens.chat

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors
import com.codehijackers.guldnet.viewmodel.MessageViewModel

@Composable
fun ChatScreen(
    squadId: String,
    onBackClicked: () -> Unit = {},
    messageViewModel: MessageViewModel = viewModel()
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    val messages by messageViewModel.messages.collectAsState()

    val squadMessages = messages.filter {
        it.squadId == squadId
    }

    var messageText by remember {
        mutableStateOf("")
    }

    val listState = rememberLazyListState()

    LaunchedEffect(squadMessages.size) {
        if (squadMessages.isNotEmpty()) {
            listState.animateScrollToItem(
                squadMessages.lastIndex
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(
                    colors.surface.copy(alpha = 0.94f)
                )
                .border(
                    width = 1.dp,
                    color = colors.border
                )
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable {
                        onBackClicked()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = strings.back,
                    tint = colors.textPrimary,
                    modifier = Modifier.size(21.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colors.selectedBackground)
                    .border(
                        width = 1.dp,
                        color = colors.primary.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Group,
                    contentDescription = null,
                    tint = colors.primary,
                    modifier = Modifier.size(19.dp)
                )
            }

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = strings.squadChat,
                    color = colors.textPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = "${strings.squadLabel} $squadId",
                    color = colors.textSecondary,
                    fontSize = 9.sp
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            state = listState,
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 12.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = squadMessages,
                key = {
                    it.id
                }
            ) { message ->
                ChatMessageBubble(
                    senderName = message.senderName,
                    content = message.content,
                    createdAt = message.createdAt
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    colors.surface.copy(alpha = 0.96f)
                )
                .border(
                    width = 1.dp,
                    color = colors.border
                )
                .navigationBarsPadding()
                .padding(
                    horizontal = 12.dp,
                    vertical = 10.dp
                ),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(13.dp))
                    .background(colors.surfaceVariant)
                    .border(
                        width = 1.dp,
                        color = colors.border,
                        shape = RoundedCornerShape(13.dp)
                    )
                    .padding(
                        horizontal = 13.dp,
                        vertical = 11.dp
                    )
            ) {
                BasicTextField(
                    value = messageText,
                    onValueChange = {
                        messageText = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        color = colors.textPrimary,
                        fontSize = 12.sp
                    ),
                    singleLine = false,
                    maxLines = 4,
                    decorationBox = { innerTextField ->
                        if (messageText.isEmpty()) {
                            Text(
                                text = strings.squadChatPlaceholder,
                                color = colors.textSecondary,
                                fontSize = 12.sp
                            )
                        }

                        innerTextField()
                    }
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (messageText.isNotBlank()) {
                            colors.primary
                        } else {
                            colors.surfaceVariant
                        }
                    )
                    .border(
                        width = 1.dp,
                        color = if (messageText.isNotBlank()) {
                            colors.primary
                        } else {
                            colors.border
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(
                        enabled = messageText.isNotBlank()
                    ) {
                        messageViewModel.sendMessage(
                            squadId = squadId,
                            content = messageText.trim(),
                            senderName = strings.you
                        )

                        messageText = ""
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Send,
                    contentDescription = strings.send,
                    tint = if (messageText.isNotBlank()) {
                        Color.White
                    } else {
                        colors.textSecondary
                    },
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}