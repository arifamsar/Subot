package com.subot.schedule.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.Route
import com.subot.schedule.ScheduleScreen
import com.subot.schedule.ScheduleDetailScreen

fun EntryProviderScope<NavKey>.scheduleFlow(
    navigator: Navigator,
    sharedTransitionScope: SharedTransitionScope,
) {
    entry<Route.Schedule> {
        ScheduleScreen(
            onScheduleClick = { id ->
                navigator.navigate(Route.ScheduleDetail(id))
            }
        )
    }

    entry<Route.ScheduleDetail> { route ->
        ScheduleDetailScreen(
            scheduleId = route.scheduleId,
            onBack = { navigator.goBack() }
        )
    }
}
