package com.sukarobot.subot.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sukarobot.subot.ui.screens.splash.SplashScreen
import com.sukarobot.subot.ui.screens.splash.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation(
    navBackStack: SnapshotStateList<Route>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavDisplay(
            backStack = navBackStack,
            onBack = {
                navBackStack.removeLastOrNull()
            },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Route.Splash> {
                    val splashViewModel = koinViewModel<SplashViewModel>()
                    val splashUiState by splashViewModel.uiState.collectAsStateWithLifecycle()

                    SplashScreen(
                        uiState = splashUiState,
                        onEvent = splashViewModel::onEvent,
                        onNavigateToHome = {
                            navBackStack.clear()
                            navBackStack.add(Route.Main)
                        },
                        onNavigateToOnboarding = {
                            navBackStack.clear()
                            navBackStack.add(Route.Onboarding)
                        },
                        onNavigateToLogin = {
                            navBackStack.clear()
                            navBackStack.add(Route.Login)
                        }
                    )
                }
                entry<Route.Onboarding>() {

                }
            }
        )
    }
}