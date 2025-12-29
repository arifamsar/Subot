package com.sukarobot.subot.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sukarobot.subot.ui.components.AppBottomNavigation
import com.sukarobot.subot.ui.screens.home.HomeScreen
import com.sukarobot.subot.ui.screens.profile.ProfileScreen
import com.sukarobot.subot.ui.screens.schedule.ScheduleScreen
import com.sukarobot.subot.ui.screens.transaction.TransactionScreen

@Composable
fun NavigationNavBar(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {}
) {
    val navigationState = rememberNavigationState(
        startRoute = AppRoute.Home,
        loginRoute = AppRoute.Login,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys,
        initialIsLoggedIn = true
    )

    val navigator = remember {
        Navigator(navigationState)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            AppBottomNavigation(
                selectedRoute = navigationState.topLevelRoute,
                onRouteSelected = {
                    navigator.navigate(it)
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            onBack = navigator::goBack,
            entries = navigationState.toEntries(
                entryProvider = entryProvider {
                    entry<AppRoute.Home>() {
                        HomeScreen()
                    }
                    entry<AppRoute.Schedule>() {
                        ScheduleScreen()
                    }
                    entry<AppRoute.Transaction>() {
                        TransactionScreen()
                    }
                    entry<AppRoute.Profile>() {
                        ProfileScreen(
                            onLogout = {
                                navigator.logout()
                                onLogout()
                            }
                        )
                    }
                }
            )
        )
    }
}