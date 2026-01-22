package com.subot.profile.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import com.subot.core.ui.navigation.Route
import com.subot.profile.ProfileScreen
import com.subot.profile.SettingsScreen
import com.subot.profile.SettingsDetailScreen

fun EntryProviderScope<Route>.profileFlow(
    rootBackStack: SnapshotStateList<Route>,
    sharedTransitionScope: SharedTransitionScope,
    onLogout: () -> Unit
) {
    entry<Route.Profile> {
        ProfileScreen(
            onNavigate = { route ->
                rootBackStack.add(route)
            },
            onLogout = onLogout
        )
    }
    entry<Route.Settings> {
        SettingsScreen(
            onBack = { rootBackStack.removeLast() }
        )
    }
    entry<Route.SettingsDetail> { route ->
        SettingsDetailScreen(
            settingId = route.settingId,
            onBack = { rootBackStack.removeLast() }
        )
    }
}
