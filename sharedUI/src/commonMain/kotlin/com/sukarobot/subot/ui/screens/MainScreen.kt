package com.sukarobot.subot.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.subot.core.ui.components.LocalBottomNavigationLiquid
import com.subot.core.ui.components.NavigationLiquidBottomBar
import com.subot.core.ui.components.SubotNavigationRail
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.RootNavigator
import com.subot.core.ui.navigation.Route
import com.subot.core.ui.navigation.TOP_LEVEL_DESTINATIONS
import com.subot.core.ui.navigation.rememberListDetailSceneStrategy
import com.subot.core.ui.navigation.rememberNavigationState
import com.subot.core.ui.navigation.toEntries
import com.subot.home.navigation.homeFlow
import com.subot.profile.navigation.profileFlow
import com.subot.schedule.navigation.scheduleFlow
import com.subot.transactions.navigation.transactionFlow
import io.github.fletchmckee.liquid.liquefiable
import io.github.fletchmckee.liquid.rememberLiquidState

// Window size class breakpoints (matching Material3 adaptive)
private val WIDTH_DP_MEDIUM_LOWER_BOUND = 600.dp
private val HEIGHT_DP_MEDIUM_LOWER_BOUND = 480.dp

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen(
    rootNavigator: RootNavigator,
    modifier: Modifier = Modifier,
) {
    // Use BoxWithConstraints to get actual screen dimensions
    // This ensures we get updated dimensions on rotation
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        MainScreenContent(
            rootNavigator = rootNavigator,
            screenWidth = maxWidth,
            screenHeight = maxHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun MainScreenContent(
    rootNavigator: RootNavigator,
    screenWidth: Dp,
    screenHeight: Dp,
    modifier: Modifier = Modifier,
) {
    val liquidState = rememberLiquidState()
    val navigationState = rememberNavigationState(
        startRoute = Route.Home,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys
    )

    val navigator = remember(navigationState) {
        Navigator(navigationState)
    }

    // Calculate window size class from actual screen dimensions
    val isWidthMedium = screenWidth >= WIDTH_DP_MEDIUM_LOWER_BOUND
    val isHeightMedium = screenHeight >= HEIGHT_DP_MEDIUM_LOWER_BOUND
    val useNavigationRail = isWidthMedium && isHeightMedium

    // Create strategy with actual screen dimensions
    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>(
        screenWidth = screenWidth,
        screenHeight = screenHeight
    )


    CompositionLocalProvider(
        LocalBottomNavigationLiquid provides liquidState
    ) {
        // Key on navigation type to force recomposition when switching between rail and bottom bar
        key(useNavigationRail) {
            Box(modifier = modifier.fillMaxSize()) {
                // Background content with liquefiable modifier for liquid glass effect
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .liquefiable(liquidState)
                ) {
                    // Navigation Rail for tablets/foldables (medium width and above)
                    if (useNavigationRail) {
                        SubotNavigationRail(
                            selectedKey = navigationState.topLevelRoute,
                            onSelectKey = {
                                navigator.navigate(it)
                            }
                        )
                    }

                    // Main content
                    Scaffold { innerPadding ->
                        NavDisplay(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            onBack = navigator::goBack,
                            sceneStrategy = listDetailStrategy,
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

                // Floating bottom navigation bar for phones (compact width)
                if (!useNavigationRail) {
                    AnimatedVisibility(
                        visible = navigationState.isOnTopLevelDestination,
                        enter = slideInVertically { it },
                        exit = slideOutVertically { it },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .navigationBarsPadding()
                    ) {
                        NavigationLiquidBottomBar(
                            selectedKey = navigationState.topLevelRoute,
                            onSelectKey = {
                                navigator.navigate(it)
                            }
                        )
                    }
                }
            }
        }
    }
}