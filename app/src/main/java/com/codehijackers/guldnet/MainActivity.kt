package com.codehijackers.guldnet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.data.local.GuildnetDatabaseProvider
import com.codehijackers.guldnet.repository.LanguageRepository
import com.codehijackers.guldnet.ui.GuildnetAuthenticatedApp
import com.codehijackers.guldnet.ui.localization.LocalGuildnetLanguage
import com.codehijackers.guldnet.ui.localization.LocalGuildnetStrings
import com.codehijackers.guldnet.ui.localization.guildnetStrings
import com.codehijackers.guldnet.viewmodel.AppViewModel
import com.codehijackers.guldnet.viewmodel.AppViewModelFactory

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GuildnetDatabaseProvider.initialize(applicationContext)

        val database = GuildnetDatabaseProvider.getDatabase()

        LanguageRepository.initialize(database)

        setContent {
            val darkBackground = Color(0xFF0B0E14)

            val appViewModel: AppViewModel = viewModel(
                factory = AppViewModelFactory(database)
            )

            val isAuthenticated by appViewModel
                .isUserAuthenticated
                .collectAsState()

            val language by LanguageRepository
                .language
                .collectAsState()

            val strings = guildnetStrings(language)

            CompositionLocalProvider(
                LocalGuildnetLanguage provides language,
                LocalGuildnetStrings provides strings
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(darkBackground)
                ) {
                    var currentScreen by remember {
                        mutableStateOf("splash")
                    }

                    if (isAuthenticated) {
                        GuildnetAuthenticatedApp()
                    } else {
                        when (currentScreen) {

                            "splash" -> {
                                SplashScreen(
                                    onSplashFinished = {
                                        currentScreen = "login"
                                    }
                                )
                            }

                            "login" -> {
                                LoginScreen(
                                    onLoginClick = {
                                        currentScreen = "biometric"
                                    },
                                    onSignUpClick = {
                                        currentScreen = "signup"
                                    },
                                    onForgotPasswordClick = {
                                    }
                                )
                            }

                            "signup" -> {
                                SignUpScreen(
                                    onSignUpClick = {
                                        currentScreen = "login"
                                    },
                                    onLoginLinkClick = {
                                        currentScreen = "login"
                                    }
                                )
                            }

                            "biometric" -> {
                                BiometricScreen(
                                    onAuthenticationSuccess = {
                                        currentScreen = "login"
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
    }
}
