package com.subot.profile.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.ListDetailScene
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.Route
import com.subot.profile.screens.profile.ProfileScreen
import com.subot.profile.screens.settings.SettingsDetailScreen
import com.subot.profile.screens.settings.SettingsScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun EntryProviderScope<NavKey>.profileFlow(
    navigator: Navigator,
    onLogout: () -> Unit
) {
    entry<Route.Profile>(
        metadata = ListDetailScene.listPane(),
    ) {
        ProfileScreen(
            onNavigate = { route ->
                navigator.navigateToDetail(route)
            },
            onLogout = onLogout
        )
    }
    entry<Route.Settings>(
        metadata = ListDetailScene.detailPane()
    ) {
        SettingsScreen(
            onBack = { navigator.goBack() }
        )
    }
    entry<Route.SettingsDetail>(
        metadata = ListDetailScene.detailPane()
    ) { route ->
        SettingsDetailScreen(
            settingId = route.settingId,
            onBack = { navigator.goBack() }
        )
    }
}
