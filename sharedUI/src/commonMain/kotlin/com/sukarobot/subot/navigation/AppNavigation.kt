package com.sukarobot.subot.navigation

import androidx.compose.animation.SharedTransitionLayout
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
import com.subot.core.ui.navigation.Route
import com.sukarobot.subot.AppState
import com.sukarobot.subot.ui.screens.MainScreen
import com.sukarobot.subot.ui.screens.login.LoginScreen
import com.sukarobot.subot.ui.screens.login.LoginViewModel
import com.sukarobot.subot.ui.screens.onboarding.OnboardingScreen
import com.sukarobot.subot.ui.screens.splash.SplashScreen
import com.sukarobot.subot.ui.screens.splash.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AppNavigation(
    backStack: SnapshotStateList<Route>,
    appState: AppState
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SharedTransitionLayout {
            NavDisplay(
                backStack = backStack,
                onBack = {
                    backStack.removeLastOrNull()
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
                                backStack.clear()
                                backStack.add(Route.Main)
                            },
                            onNavigateToOnboarding = {
                                backStack.clear()
                                backStack.add(Route.Onboarding)
                            },
                            onNavigateToLogin = {
                                backStack.clear()
                                backStack.add(Route.Login)
                            }
                        )
                    }
                    entry<Route.Onboarding> {
                        OnboardingScreen(
                            onOnboardingComplete = {
                                backStack.clear()
                                backStack.add(Route.Login)
                            }
                        )
                    }

                    entry<Route.Login> {
                        val viewModel = koinViewModel<LoginViewModel>()
                        val loginUiState by viewModel.uiState.collectAsStateWithLifecycle()

                        LoginScreen(
                            uiState = loginUiState,
                            onEvent = viewModel::onEvent,
                            onLoginSuccess = {
                                backStack.clear()
                                backStack.add(Route.Main)
                            }
                        )
                    }

                    entry<Route.Main> {
                        MainScreen(
                            appState = appState,
                            homeBackStack = appState.homeBackStack
                        )
                    }
                }
            )
        }
    }
}