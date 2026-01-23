package com.sukarobot.subot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.subot.core.ui.navigation.Route
import com.subot.core.ui.navigation.RootNavigator

@Composable
fun rememberAppState(
    rootBackStack: SnapshotStateList<Route> = remember { mutableStateListOf(Route.Splash) },
) : AppState {
    return remember(rootBackStack) {
        AppState(rootBackStack)
    }
}

@Stable
class AppState(
    val rootBackStack: SnapshotStateList<Route>
) {
    val rootNavigator = RootNavigator(rootBackStack)
}
