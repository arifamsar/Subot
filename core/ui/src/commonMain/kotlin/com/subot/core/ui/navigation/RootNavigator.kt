package com.subot.core.ui.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * Navigator for root/auth flow navigation.
 * Handles navigation between Splash, Onboarding, Login, and Main screens.
 */
class RootNavigator(
    val backStack: SnapshotStateList<Route>
) {
    fun navigateToOnboarding() {
        backStack.clear()
        backStack.add(Route.Onboarding)
    }

    fun navigateToLogin() {
        backStack.clear()
        backStack.add(Route.Login)
    }

    fun navigateToMain() {
        backStack.clear()
        backStack.add(Route.Main)
    }

    fun logout() {
        backStack.clear()
        backStack.add(Route.Login)
    }

    fun goBack(): Boolean {
        return backStack.removeLastOrNull() != null
    }
}
