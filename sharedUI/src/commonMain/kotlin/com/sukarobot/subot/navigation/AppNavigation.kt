package com.sukarobot.subot.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.subot.core.ui.navigation.Route
import com.sukarobot.subot.AppState
import com.sukarobot.subot.ui.screens.MainScreen
import com.sukarobot.subot.ui.screens.forgot_password.ForgotPasswordScreen
import com.sukarobot.subot.ui.screens.forgot_password.ForgotPasswordViewModel
import com.sukarobot.subot.ui.screens.login.LoginScreen
import com.sukarobot.subot.ui.screens.login.LoginViewModel
import com.sukarobot.subot.ui.screens.onboarding.OnboardingScreen
import com.sukarobot.subot.ui.screens.splash.SplashScreen
import com.sukarobot.subot.ui.screens.splash.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation(
    appState: AppState,
    modifier: Modifier = Modifier
) {
    val rootNavigator = appState.rootNavigator

    SharedTransitionLayout(
        modifier = modifier
    ) {
        NavDisplay(
            backStack = appState.rootBackStack,
            onBack = {
                rootNavigator.goBack()
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
                        onNavigateToHome = rootNavigator::navigateToMain,
                        onNavigateToOnboarding = rootNavigator::navigateToOnboarding,
                        onNavigateToLogin = rootNavigator::navigateToLogin
                    )
                }
                entry<Route.Onboarding> {
                    OnboardingScreen(
                        onOnboardingComplete = rootNavigator::navigateToLogin
                    )
                }

                entry<Route.Login> {
                    val viewModel = koinViewModel<LoginViewModel>()
                    val loginUiState by viewModel.uiState.collectAsStateWithLifecycle()

                    LoginScreen(
                        uiState = loginUiState,
                        onEvent = viewModel::onEvent,
                        onLoginSuccess = rootNavigator::navigateToMain,
                        navigateToForgot = {
                            rootNavigator.backStack.add(Route.ForgetPassword)
                        }
                    )
                }

                entry<Route.ForgetPassword> {
                    val viewModel = koinViewModel<ForgotPasswordViewModel>()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    ForgotPasswordScreen(
                        uiState = uiState,
                        onEvent = viewModel::onEvent,
                        onBack = {
                            rootNavigator.goBack()
                        }
                    )
                }

                entry<Route.Main> {
                    MainScreen(
                        rootNavigator = rootNavigator
                    )
                }
            }
        )
    }
}