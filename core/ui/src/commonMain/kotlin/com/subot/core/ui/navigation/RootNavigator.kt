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

    fun navigateToPortal() {
        backStack.clear()
        backStack.add(Route.Portal)
    }

    fun navigateToLogin(loginType: String = "MITRA") {
        backStack.add(Route.Login(loginType))
    }

    fun navigateToMain() {
        backStack.clear()
        backStack.add(Route.Main)
    }

    fun logout() {
        backStack.clear()
        backStack.add(Route.Portal)
    }

    fun goBack(): Boolean {
        return backStack.removeLastOrNull() != null
    }
}
