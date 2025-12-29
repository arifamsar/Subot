package com.sukarobot.subot.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.sukarobot.subot.ui.components.AppBottomNavigation

/**
 * Main screen wrapper that displays bottom navigation and the current screen content
 */
@Composable
fun MainScreen(
    currentTopLevelRoute: NavKey,
    onBottomNavClick: (NavKey) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AppBottomNavigation(
                selectedRoute = currentTopLevelRoute,
                onRouteSelected = onBottomNavClick
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            content()
        }
    }
}
