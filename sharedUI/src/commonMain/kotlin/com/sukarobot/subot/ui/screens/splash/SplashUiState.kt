package com.sukarobot.subot.ui.screens.splash

data class SplashUiState(
    val isOnboardingCompleted: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val isReady: Boolean = false,
    val error: String? = null
)