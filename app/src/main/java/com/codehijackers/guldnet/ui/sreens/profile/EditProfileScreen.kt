package com.codehijackers.guldnet.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.PersonOutline
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
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors
import com.codehijackers.guldnet.viewmodel.ProfileViewModel

@Composable
fun EditProfileScreen(
    onBackClicked: () -> Unit = {},
    profileViewModel: ProfileViewModel = viewModel()
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    val profile by profileViewModel.profile.collectAsState()

    val currentProfile = profile

    var displayName by remember(currentProfile?.displayName) {
        mutableStateOf(currentProfile?.displayName ?: "")
    }

    var username by remember(currentProfile?.username) {
        mutableStateOf(currentProfile?.username ?: "")
    }

    var bio by remember(currentProfile?.bio) {
        mutableStateOf(currentProfile?.bio ?: "")
    }

    val isValid =
        displayName.isNotBlank() &&
                username.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(
                horizontal = 10.dp,
                vertical = 14.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
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
                modifier = Modifier.width(7.dp)
            )

            Text(
                text = strings.editProfile,
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(22.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(17.dp))
                .background(colors.surface)
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = RoundedCornerShape(17.dp)
                )
                .padding(
                    vertical = 22.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                        .background(colors.primary)
                        .border(
                            width = 2.dp,
                            color = colors.accent,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = profile?.displayName ?: "X",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.height(9.dp)
                )

                Text(
                    text = strings.changeAvatar,
                    color = colors.primary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        EditProfileField(
            label = strings.displayName,
            value = displayName,
            onValueChange = {
                displayName = it
            },
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(13.dp)
        )

        EditProfileField(
            label = strings.username,
            value = username,
            onValueChange = {
                username = it
            },
            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(13.dp)
        )

        EditProfileField(
            label = strings.bio,
            value = bio,
            onValueChange = {
                bio = it
            },
            singleLine = false
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .clip(RoundedCornerShape(11.dp))
                .background(
                    if (isValid) {
                        colors.primary
                    } else {
                        colors.surfaceVariant
                    }
                )
                .border(
                    width = 1.dp,
                    color = if (isValid) {
                        colors.primary
                    } else {
                        colors.border
                    },
                    shape = RoundedCornerShape(11.dp)
                )
                .clickable(
                    enabled = isValid
                ) {
                    profileViewModel.updateProfile(
                        displayName = displayName.trim(),
                        username = username.trim(),
                        bio = bio.trim()
                    )

                    onBackClicked()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = strings.saveChanges,
                color = if (isValid) {
                    Color.White
                } else {
                    colors.textSecondary
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(
            modifier = Modifier.height(9.dp)
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
                text = strings.cancel,
                color = colors.textSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun EditProfileField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean
) {
    val colors = currentGuildnetThemeColors

    Column {
        Text(
            text = label.uppercase(),
            color = colors.textSecondary,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.7.sp,
            modifier = Modifier.padding(
                start = 3.dp,
                bottom = 6.dp
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(colors.surface)
                .border(
                    width = 1.dp,
                    color = colors.border,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(
                    horizontal = 13.dp,
                    vertical = if (singleLine) 12.dp else 11.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.PersonOutline,
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.size(17.dp)
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = singleLine,
                maxLines = if (singleLine) 1 else 4,
                textStyle = TextStyle(
                    color = colors.textPrimary,
                    fontSize = 12.sp
                )
            )
        }
    }
}