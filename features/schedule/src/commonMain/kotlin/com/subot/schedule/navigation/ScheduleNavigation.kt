package com.subot.schedule.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.Route
import com.subot.schedule.ScheduleScreen

fun EntryProviderScope<NavKey>.scheduleFlow(
    navigator: Navigator,
) {
    entry<Route.Schedule> {
        ScheduleScreen()
    }
}
