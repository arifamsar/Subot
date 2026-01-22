package com.subot.home.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import com.subot.core.ui.navigation.Route
import com.subot.home.HomeScreen

fun EntryProviderScope<Route>.homeFlow(
    rootBackStack: SnapshotStateList<Route>,
    sharedTransitionScope: SharedTransitionScope
) {
    entry<Route.Home> {
        HomeScreen()
    }
}