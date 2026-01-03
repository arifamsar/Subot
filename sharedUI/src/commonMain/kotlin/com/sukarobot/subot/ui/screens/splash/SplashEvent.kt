package com.sukarobot.subot.ui.screens.splash

/**
 * Sealed class representing all possible UI events for the splash screen
 */
sealed class SplashEvent {
    object LoadStatuses : SplashEvent()
}