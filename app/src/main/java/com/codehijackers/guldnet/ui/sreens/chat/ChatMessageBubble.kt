package com.codehijackers.guldnet.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val GuildnetSurface = Color(0xFF0F1727)
private val GuildnetBorder = Color(0xFF26344D)
private val GuildnetPurple = Color(0xFF9857FF)
private val GuildnetText = Color(0xFFF1F3FA)
private val GuildnetMutedText = Color(0xFF8794AD)

@Composable
fun ChatMessageBubble(
    senderName: String,
    content: String,
    createdAt: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    GuildnetSurface,
                    RoundedCornerShape(13.dp)
                )
                .border(
                    width = 1.dp,
                    color = GuildnetBorder,
                    shape = RoundedCornerShape(13.dp)
                )
                .padding(
                    horizontal = 13.dp,
                    vertical = 10.dp
                )
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = senderName,
                    color = GuildnetPurple,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    text = createdAt,
                    color = GuildnetMutedText,
                    fontSize = 8.sp
                )
            }

            Text(
                text = content,
                color = GuildnetText,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                modifier = Modifier.padding(
                    top = 5.dp
                )
            )
        }
    }
}