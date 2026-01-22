package com.subot.schedule.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import com.subot.core.ui.navigation.Route
import com.subot.schedule.ScheduleScreen

fun EntryProviderScope<Route>.scheduleFlow(
    rootBackStack: SnapshotStateList<Route>,
    sharedTransitionScope: SharedTransitionScope
) {
    entry<Route.Schedule> {
        ScheduleScreen()
    }
}
