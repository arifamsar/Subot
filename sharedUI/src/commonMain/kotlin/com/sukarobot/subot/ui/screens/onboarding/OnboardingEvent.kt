package com.sukarobot.subot.ui.screens.onboarding

/**
 * Sealed interface representing all possible UI events for the onboarding screen
 */
sealed interface OnboardingEvent {
    data object CompleteOnboarding : OnboardingEvent
    data class ToggleDarkMode(val enabled: Boolean) : OnboardingEvent
}
