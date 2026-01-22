package com.sukarobot.subot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.subot.core.ui.navigation.Route
import com.subot.core.ui.navigation.TopLevelDestination

@Composable
fun rememberAppState(
    navBackStack: SnapshotStateList<Route> = remember { mutableStateListOf(Route.Splash) },
    homeBackStack: SnapshotStateList<Route> = remember { mutableStateListOf(Route.Home) }
) : AppState {
    return remember(navBackStack, homeBackStack) {
        AppState(navBackStack, homeBackStack)
    }
}

@Stable
class AppState(
    val navBackStack: SnapshotStateList<Route>,
    val homeBackStack: SnapshotStateList<Route>
) {
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
    private val bottomBarRoutes = TopLevelDestination.entries.map { it.route }

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = TopLevelDestination.entries.firstOrNull {
            homeBackStack.lastOrNull() == it.route
        }

    @Composable
    fun showBottomBar(): Boolean {
        // Show bottom bar if we are in Main route AND the current home route supports it
        val isMainRoute = navBackStack.lastOrNull() == Route.Main
        val isBottomBarRoute = homeBackStack.lastOrNull() in bottomBarRoutes
        return isMainRoute && isBottomBarRoute
    }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        // Ensure we are in Main route
        if (navBackStack.lastOrNull() != Route.Main) {
            navBackStack.clear()
            navBackStack.add(Route.Main)
        }
        
        // Navigate within Home backstack
        when (topLevelDestination) {
            TopLevelDestination.HOME -> {
                homeBackStack.clear()
                homeBackStack.add(Route.Home)
            }
            TopLevelDestination.SCHEDULE -> {
                homeBackStack.clear()
                homeBackStack.add(Route.Schedule)
            }
            TopLevelDestination.TRANSACTION -> {
                homeBackStack.clear()
                homeBackStack.add(Route.Transaction)
            }
            TopLevelDestination.PROFILE -> {
                homeBackStack.clear()
                homeBackStack.add(Route.Profile)
            }
        }
    }

}
