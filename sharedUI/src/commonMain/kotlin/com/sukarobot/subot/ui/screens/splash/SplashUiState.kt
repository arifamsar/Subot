package com.sukarobot.subot.ui.screens.splash

/**
 * UI state for the splash screen following MVI pattern
 */
data class SplashUiState(
    val isOnboardingCompleted: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val isReady: Boolean = false,
    val error: String? = null
) {
    /**
     * Determines the next screen based on the current state
     */
    val nextScreen: NextScreen
        get() = when {
            isLoggedIn -> NextScreen.Home
            isOnboardingCompleted -> NextScreen.Login
            else -> NextScreen.Onboarding
        }
}

/**
 * Enum representing possible next screens from splash
 */
enum class NextScreen {
    Home, Login, Onboarding
}