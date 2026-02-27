package com.sukarobot.subot.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.sukarobot.subot.ui.screens.login.LoginType
import com.sukarobot.subot.ui.screens.login.LoginViewModel
import com.sukarobot.subot.ui.screens.onboarding.OnboardingScreen
import com.sukarobot.subot.ui.screens.portal.PortalScreen
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
            transitionSpec = {
                slideInHorizontally { it } + fadeIn() togetherWith
                        slideOutHorizontally { -it } + fadeOut()
            },
            popTransitionSpec = {
                slideInHorizontally { -it } + fadeIn() togetherWith
                        slideOutHorizontally { it } + fadeOut()
            },
            predictivePopTransitionSpec = {
                slideInHorizontally { -it } + fadeIn() togetherWith
                        slideOutHorizontally { it } + fadeOut()
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
                        onNavigateToLogin = rootNavigator::navigateToPortal
                    )
                }
                entry<Route.Onboarding> {
                    OnboardingScreen(
                        onOnboardingComplete = rootNavigator::navigateToPortal
                    )
                }

                entry<Route.Portal> {
                    PortalScreen(
                        onMitraSelected = {
                            rootNavigator.navigateToLogin(LoginType.MITRA.name)
                        },
                        onMemberSelected = {
                            rootNavigator.navigateToLogin(LoginType.MEMBER.name)
                        }
                    )
                }

                entry<Route.Login> { route ->
                    val viewModel = koinViewModel<LoginViewModel>()
                    val loginUiState by viewModel.uiState.collectAsStateWithLifecycle()

                    // Set the login type from the route parameter
                    LaunchedEffect(route.loginType) {
                        val loginType = try {
                            LoginType.valueOf(route.loginType)
                        } catch (_: Exception) {
                            LoginType.MITRA
                        }
                        viewModel.setInitialLoginType(loginType)
                    }

                    LoginScreen(
                        uiState = loginUiState,
                        schoolsPagingData = viewModel.schoolsPagingData,
                        onEvent = viewModel::onEvent,
                        navigateBack = { rootNavigator.goBack() },
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