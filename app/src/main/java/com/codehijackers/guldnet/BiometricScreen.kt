package com.codehijackers.guldnet

import androidx.fragment.app.FragmentActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.codehijackers.guldnet.repository.LanguageRepository
import com.codehijackers.guldnet.ui.localization.currentGuildnetStrings
import com.codehijackers.guldnet.ui.theme.currentGuildnetThemeColors

@Composable
fun BiometricScreen(
    userId: Long,
    onAuthenticationSuccess: (Long) -> Unit = {},
    onBackClick: () -> Unit = {},
    onUsePasswordClick: () -> Unit = {},
    onCancelClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity

    val language by LanguageRepository.language.collectAsState()
    val strings = currentGuildnetStrings
    val colors = currentGuildnetThemeColors

    LaunchedEffect(language) {
        if (activity == null) {
            return@LaunchedEffect
        }

        val biometricManager = BiometricManager.from(context)

        val canAuthenticate =
            biometricManager.canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.BIOMETRIC_WEAK
            )

        if (canAuthenticate != BiometricManager.BIOMETRIC_SUCCESS) {
            return@LaunchedEffect
        }

        val executor = ContextCompat.getMainExecutor(context)

        val biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)

                    onAuthenticationSuccess(userId)
                }

                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(
                        errorCode,
                        errString
                    )

                    onCancelClick()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Guildnet")
            .setSubtitle(strings.authenticateToContinue)
            .setDescription(strings.biometricDescription)
            .setNegativeButtonText(strings.usePassword)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    BiometricContent(
        onBackClick = onBackClick,
        onCancelClick = onCancelClick
    )
}

@Composable
private fun BiometricContent(
    onBackClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    val colors = currentGuildnetThemeColors
    val strings = currentGuildnetStrings

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = strings.back,
            tint = colors.textPrimary,
            modifier = Modifier
                .padding(24.dp)
                .size(28.dp)
                .clickable {
                    onBackClick()
                }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = strings.biometricAuth,
                color = colors.textPrimary,
                fontSize = 28.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = strings.authenticateToContinue,
                color = colors.textSecondary,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(50.dp)
            )

            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(
                        colors.selectedBackground,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "👆",
                    fontSize = 55.sp
                )
            }

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            Text(
                text = strings.useDeviceBiometric,
                color = colors.textPrimary,
                fontSize = 15.sp
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = strings.fingerprintOrFace,
                color = colors.textSecondary,
                fontSize = 13.sp
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            Text(
                text = strings.cancel,
                color = colors.textSecondary,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    onCancelClick()
                }
            )
        }
    }
}