package com.subot.home.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import com.sukarobot.subot.navigation.AppRoute

fun EntryProviderScope<AppRoute>.homeFlow(
    rootBackStack: SnapshotStateList<AppRoute>,
    sharedTransitionScope: SharedTransitionScope
) {
}