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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

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

    LaunchedEffect(Unit) {

        if (activity == null) {
            return@LaunchedEffect
        }

        val biometricManager = BiometricManager.from(context)

        val canAuthenticate =
            biometricManager.canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.BIOMETRIC_WEAK
            )

        if (
            canAuthenticate != BiometricManager.BIOMETRIC_SUCCESS
        ) {
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

                    // The biometric didn't match.
                    // Android will normally allow the user
                    // to try again.
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Guildnet")
            .setSubtitle("Authenticate to continue")
            .setDescription(
                "Use your device biometric security to sign in."
            )
            .setNegativeButtonText("Use Password")
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

    val darkBg = Color(0xFF0B0E14)
    val textBodyColor = Color(0xFF94A3B8)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBg)
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
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
                text = "Biometric Auth",
                color = Color.White,
                fontSize = 28.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Authenticate to continue",
                color = textBodyColor,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(50.dp)
            )

            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(
                        Color(0xFF332047),
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
                text = "Use your device biometric",
                color = Color.White,
                fontSize = 15.sp
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Fingerprint or face recognition",
                color = textBodyColor,
                fontSize = 13.sp
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            Text(
                text = "Cancel",
                color = textBodyColor,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    onCancelClick()
                }
            )
        }
    }
}