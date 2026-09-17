package com.codehijackers.guildnet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkBackground = Color(0xFF0B0E14)

            // Wrap everything in a full-screen Box with the dark theme color
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(darkBackground)
            ) {
                // Default to splash screen when app launches
                var currentScreen by remember { mutableStateOf("splash") }

                when (currentScreen) {
                    "splash" -> SplashScreen(
                        onSplashFinished = { currentScreen = "login" }
                    )
                    "login" -> LoginScreen(
                        onLoginClick = { currentScreen = "biometric" },
                        onSignUpClick = { currentScreen = "signup" },
                        onForgotPasswordClick = {}
                    )
                    "signup" -> SignUpScreen(
                        onSignUpClick = { currentScreen = "login" },
                        onLoginLinkClick = { currentScreen = "login" }
                    )
                    "biometric" -> BiometricScreen(
                        onBackClick = { currentScreen = "login" },
                        onUsePasswordClick = { currentScreen = "login" },
                        onCancelClick = { currentScreen = "login" }
                    )
                }
            }
        }
    }
}