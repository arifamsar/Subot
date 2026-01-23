package com.subot.profile.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.Route
import com.subot.profile.ProfileScreen
import com.subot.profile.SettingsScreen
import com.subot.profile.SettingsDetailScreen

fun EntryProviderScope<NavKey>.profileFlow(
    navigator: Navigator,
    onLogout: () -> Unit
) {
    entry<Route.Profile> {
        ProfileScreen(
            onNavigate = { route ->
                navigator.navigate(route)
            },
            onLogout = onLogout
        )
    }
    entry<Route.Settings> {
        SettingsScreen(
            onBack = { navigator.goBack() }
        )
    }
    entry<Route.SettingsDetail> { route ->
        SettingsDetailScreen(
            settingId = route.settingId,
            onBack = { navigator.goBack() }
        )
    }
}
