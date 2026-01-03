package com.sukarobot.subot.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sukarobot.subot.ui.screens.login.LoginScreen
import com.sukarobot.subot.ui.screens.login.LoginViewModel
import com.sukarobot.subot.ui.screens.onboarding.OnboardingScreen
import com.sukarobot.subot.ui.screens.onboarding.OnboardingViewModel
import com.sukarobot.subot.ui.screens.splash.SplashScreen
import com.sukarobot.subot.ui.screens.splash.SplashViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

/**
 * Root Nav3 host for the app. Handles splash → onboarding → login → main (bottom nav) flow.
 */
@Composable
fun AppNavHost() {
    val coroutineScope = rememberCoroutineScope()

    val navigationState = rememberNavigationState(
        startRoute = AppRoute.Splash,
        loginRoute = AppRoute.Login,
        topLevelRoutes = setOf(
            AppRoute.Splash,
            AppRoute.Onboarding,
            AppRoute.Login,
            AppRoute.Main
        ),
        initialIsLoggedIn = false
    )

    val navigator = remember { Navigator(navigationState) }

    NavDisplay(
        onBack = navigator::goBack,
        entries = navigationState.toEntries(
            entryProvider = entryProvider {
                entry<AppRoute.Splash> {
                    val splashViewModel = koinViewModel<SplashViewModel>()
                    val splashUiState by splashViewModel.uiState.collectAsStateWithLifecycle()

                    SplashScreen(
                        uiState = splashUiState,
                        onEvent = splashViewModel::onEvent,
                        onNavigateToHome = {
                            navigationState.startRoute = AppRoute.Main
                            navigator.onLoginSuccess()
                        },
                        onNavigateToOnboarding = {
                            navigationState.startRoute = AppRoute.Onboarding
                            navigator.navigate(AppRoute.Onboarding)
                        },
                        onNavigateToLogin = {
                            navigationState.startRoute = AppRoute.Login
                            navigator.navigate(AppRoute.Login)
                        }
                    )
                }

                entry<AppRoute.Onboarding> {

                    val onboardingViewModel = koinViewModel<OnboardingViewModel>()
                    OnboardingScreen(
                        onOnboardingComplete = {
                            coroutineScope.launch {
                                onboardingViewModel.completeOnboarding()
                                navigationState.startRoute = AppRoute.Login
                                navigator.navigate(AppRoute.Login)
                            }
                        }
                    )
                }

                entry<AppRoute.Login> {
                    val viewModel = koinViewModel<LoginViewModel>()
                    val loginUiState by viewModel.uiState.collectAsStateWithLifecycle()

                    LoginScreen(
                        uiState = loginUiState,
                        onEvent = viewModel::onEvent,
                        onLoginSuccess = navigator::onLoginSuccess
                    )
                }

                entry<AppRoute.Main> {
                    NavigationNavBar(
                        onLogout = navigator::logout
                    )
                }
            }
        )
    )
}
