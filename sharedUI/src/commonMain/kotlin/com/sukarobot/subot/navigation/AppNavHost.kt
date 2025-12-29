package com.sukarobot.subot.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sukarobot.subot.ui.screens.login.LoginScreen
import com.sukarobot.subot.ui.screens.login.LoginViewModel
import com.sukarobot.subot.ui.screens.onboarding.OnboardingScreen
import com.sukarobot.subot.ui.screens.splash.SplashScreen

/**
 * Root Nav3 host for the app. Handles splash → onboarding → login → main (bottom nav) flow.
 */
@Composable
fun AppNavHost() {
    // Track onboarding completion locally; wire to persistence later if needed.
    var onboardingCompleted by rememberSaveable { mutableStateOf(false) }

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
    val loginViewModel = remember { LoginViewModel() }

    NavDisplay(
        onBack = navigator::goBack,
        entries = navigationState.toEntries(
            entryProvider = entryProvider {
                entry<AppRoute.Splash> {
                    SplashScreen(
                        onSplashComplete = {
                            // Decide next step; go onboarding unless already completed.
                            navigator.navigate(
                                if (onboardingCompleted) AppRoute.Login else AppRoute.Onboarding
                            )
                        }
                    )
                }

                entry<AppRoute.Onboarding> {
                    OnboardingScreen(
                        onOnboardingComplete = {
                            onboardingCompleted = true
                            navigator.navigate(AppRoute.Login)
                        }
                    )
                }

                entry<AppRoute.Login> {
                    LoginScreen(
                        viewModel = loginViewModel,
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
