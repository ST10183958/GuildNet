package com.codehijackers.guldnet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.GuildnetAuthenticatedApp
import com.codehijackers.guldnet.viewmodel.AppViewModel

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val darkBackground = Color(0xFF0B0E14)

            val appViewModel: AppViewModel = viewModel()

            val isAuthenticated by appViewModel
                .isUserAuthenticated
                .collectAsState()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(darkBackground)
            ) {

                var currentScreen by remember {
                    mutableStateOf("splash")
                }

                if (isAuthenticated) {

                    // User has successfully authenticated.
                    // Enter the normal Guildnet application.
                    GuildnetAuthenticatedApp()

                } else {

                    when (currentScreen) {

                        "splash" -> SplashScreen(
                            onSplashFinished = {
                                appViewModel.setAuthenticated(true)
                            }
                        )

                        "login" -> LoginScreen(
                            onLoginClick = {
                                appViewModel.setAuthenticated(true)
                            },
                            onSignUpClick = {
                                currentScreen = "signup"
                            },
                            onForgotPasswordClick = {
                                // TODO: Forgot password
                            }
                        )

                        "signup" -> SignUpScreen(
                            onSignUpClick = {
                                currentScreen = "login"
                            },
                            onLoginLinkClick = {
                                currentScreen = "login"
                            }
                        )

                        "biometric" -> BiometricScreen(
                            onAuthenticationSuccess = {
                                appViewModel.setAuthenticated(true)
                            },
                            onBackClick = {
                                currentScreen = "login"
                            },
                            onUsePasswordClick = {
                                currentScreen = "login"
                            },
                            onCancelClick = {
                                currentScreen = "login"
                            }
                        )
                    }
                }
            }
        }
    }
}