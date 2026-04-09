package com.subot.schedule.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.Route
import com.subot.schedule.ScheduleScreen

fun EntryProviderScope<NavKey>.scheduleFlow(
    navigator: Navigator,
    sharedTransitionScope: SharedTransitionScope,
) {
    entry<Route.Schedule> {
        ScheduleScreen()
    }
}
