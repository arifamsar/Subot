package com.sukarobot.subot.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sukarobot.subot.AppState
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    appState: AppState
) {
    AnimatedVisibility(
        visible = appState.showBottomBar(),
        modifier = modifier
    ) {
        NavigationBar(
            tonalElevation = 0.dp,
        ) {
            appState.topLevelDestinations.forEach { route ->
                val selected = appState.currentTopLevelDestination == route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        appState.navigateToTopLevelDestination(route)
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) route.selectedIcon else route.icon,
                            contentDescription = stringResource(route.label)
                        )
                    },
                    label = {
                        Text(text = stringResource(route.label))
                    },
                )
            }
        }
    }
}