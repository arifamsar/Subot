package com.sukarobot.subot.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.subot.core.ui.components.SubotNavigationBar
import com.subot.core.ui.components.SubotNavigationRail
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.RootNavigator
import com.subot.core.ui.navigation.Route
import com.subot.core.ui.navigation.TOP_LEVEL_DESTINATIONS
import com.subot.core.ui.navigation.rememberNavigationState
import com.subot.core.ui.navigation.toEntries
import com.subot.home.navigation.homeFlow
import com.subot.profile.navigation.profileFlow
import com.subot.schedule.navigation.scheduleFlow
import com.subot.transactions.navigation.transactionFlow

@Composable
fun MainScreen(
    rootNavigator: RootNavigator,
    modifier: Modifier = Modifier,
) {
    val navigationState = rememberNavigationState(
        startRoute = Route.Home,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys
    )

    val navigator = remember(navigationState) {
        Navigator(navigationState)
    }

    BoxWithConstraints(modifier = modifier) {
        val isTablet = maxWidth >= 600.dp

        Row(modifier = Modifier.fillMaxSize()) {
            if (isTablet) {
                AnimatedVisibility(
                    visible = navigationState.isOnTopLevelDestination,
                    enter = slideInHorizontally { -it },
                    exit = slideOutHorizontally { -it }
                ) {
                    SubotNavigationRail(
                        selectedKey = navigationState.topLevelRoute,
                        onSelectKey = {
                            navigator.navigate(it)
                        }
                    )
                }
            }

            Scaffold(
                modifier = Modifier.weight(1f),
                bottomBar = {
                    if (!isTablet) {
                        AnimatedVisibility(
                            visible = navigationState.isOnTopLevelDestination,
                            enter = slideInVertically { it },
                            exit = slideOutVertically { it }
                        ) {
                            SubotNavigationBar(
                                selectedKey = navigationState.topLevelRoute,
                                onSelectKey = {
                                    navigator.navigate(it)
                                }
                            )
                        }
                    }
                }
            ) { innerPadding ->
                NavDisplay(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    onBack = navigator::goBack,
                    entries = navigationState.toEntries(
                        entryProvider {
                            homeFlow(navigator = navigator)
                            scheduleFlow(navigator = navigator)
                            transactionFlow(navigator = navigator)
                            profileFlow(
                                navigator = navigator,
                                onLogout = rootNavigator::logout
                            )
                        }
                    )
                )
            }
        }
    }
}