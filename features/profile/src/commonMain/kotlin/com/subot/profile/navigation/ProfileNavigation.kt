package com.subot.profile.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.ListDetailScene
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.Route
import com.subot.profile.screens.members.MembersScreen
import com.subot.profile.screens.members.MembersViewModel
import com.subot.profile.screens.penanggung_jawab.PenanggungJawabScreen
import com.subot.profile.screens.penanggung_jawab.PenanggungJawabViewModel
import com.subot.profile.screens.profile.ProfileScreen
import com.subot.profile.screens.settings.SettingsDetailScreen
import com.subot.profile.screens.settings.SettingsScreen
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun EntryProviderScope<NavKey>.profileFlow(
    navigator: Navigator,
    sharedTransitionScope: SharedTransitionScope,
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
//        metadata = ListDetailScene.listPane() + ListDetailScene.detailPane()
        metadata = ListDetailScene.detailPane(),
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
    entry<Route.Members>(
        metadata = ListDetailScene.detailPane()
    ) {
        val membersViewModel: MembersViewModel = koinViewModel()
        MembersScreen(
            viewModel = membersViewModel,
            onBack = { navigator.goBack() }
        )
    }
    entry<Route.PenanggungJawab>(
        metadata = ListDetailScene.detailPane()
    ) {
        val penanggungJawabViewModel: PenanggungJawabViewModel = koinViewModel()
        PenanggungJawabScreen(
            viewModel = penanggungJawabViewModel,
            onBack = { navigator.goBack() }
        )
    }
}
