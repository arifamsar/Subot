package com.sukarobot.subot.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.key
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.subot.core.ui.components.SubotNavigationBar
import com.subot.core.ui.components.SubotNavigationRail
import com.subot.core.ui.navigation.ListDetailSceneStrategy
import com.subot.core.ui.navigation.NavigationState
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

    // Key on navigation type to force recomposition when switching between rail and bottom bar
    key(useNavigationRail) {
        Box(modifier = modifier.fillMaxSize()) {
            // Background content
            Row(
                modifier = Modifier.fillMaxSize()
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

                // Main content area with Scaffold-managed bottom bar
                MainContent(
                    modifier = Modifier.weight(1f).fillMaxSize(),
                    navigationState = navigationState,
                    navigator = navigator,
                    rootNavigator = rootNavigator,
                    listDetailStrategy = listDetailStrategy,
                    useNavigationRail = useNavigationRail
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    navigationState: NavigationState,
    navigator: Navigator,
    rootNavigator: RootNavigator,
    listDetailStrategy: ListDetailSceneStrategy<NavKey>,
    useNavigationRail: Boolean,
    modifier: Modifier = Modifier,
) {
    val isTopLevel = navigationState.isOnTopLevelDestination
    
    // State to track if the bottom bar should be visible based on scroll
    var isBottomBarVisible by remember(navigationState.topLevelRoute) { mutableStateOf(true) }
    
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // If scrolling down (available.y < 0), hide the bar
                // If scrolling up (available.y > 0), show the bar
                if (available.y < -1f) {
                    isBottomBarVisible = false
                } else if (available.y > 1f) {
                    isBottomBarVisible = true
                }
                return Offset.Zero
            }
        }
    }

    // Only show bottom bar if on top level, not using rail, and scroll state says so
    val showBottomBar = !useNavigationRail && isTopLevel && isBottomBarVisible

    // Get the system navigation bar height for edge-to-edge
    val systemNavBarPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    
    // Total height of the bottom bar (Material 3 NavigationBar is typically 80dp)
    val bottomBarHeight = 80.dp 

    // Animate bottom padding for the content to avoid jumping and ensure edge-to-edge support
    val animatedBottomPadding by animateDpAsState(
        targetValue = if (showBottomBar) bottomBarHeight + systemNavBarPadding else systemNavBarPadding,
        label = "bottomPadding"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = animatedBottomPadding),
            onBack = navigator::goBack,
            sceneStrategy = listDetailStrategy,
            transitionSpec = {
                if (isTopLevel) {
                    fadeIn() togetherWith fadeOut()
                } else {
                    slideInHorizontally { it } + fadeIn() togetherWith
                            slideOutHorizontally { -it } + fadeOut()
                }
            },
            popTransitionSpec = {
                if (isTopLevel) {
                    fadeIn() togetherWith fadeOut()
                } else {
                    slideInHorizontally { -it } + fadeIn() togetherWith
                            slideOutHorizontally { it } + fadeOut()
                }
            },
            predictivePopTransitionSpec = {
                if (isTopLevel) {
                    fadeIn() togetherWith fadeOut()
                } else {
                    slideInHorizontally { -it } + fadeIn() togetherWith
                            slideOutHorizontally { it } + fadeOut()
                }
            },
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

        AnimatedVisibility(
            visible = showBottomBar,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut()
        ) {
            SubotNavigationBar(
                selectedKey = navigationState.topLevelRoute,
                onSelectKey = { navigator.navigate(it) }
            )
        }
    }
}
