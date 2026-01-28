package com.subot.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.Route
import com.subot.home.screens.home.HomeScreen

fun EntryProviderScope<NavKey>.homeFlow(
    navigator: Navigator,
) {
    entry<Route.Home> {
        HomeScreen()
    }
}