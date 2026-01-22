package com.sukarobot.subot.ui.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.subot.core.ui.theme.BluePrimary
import com.subot.core.ui.theme.LocalThemeIsDark
import com.subot.core.ui.components.AppPrimaryButton
import com.subot.core.ui.components.AppTextButton
import com.subot.core.ui.components.WaterDropIndicator
import com.subot.core.ui.components.switch_button.SwitchButton
import com.subot.core.ui.components.switch_button.SwitchButtonConfig
import com.subot.core.ui.components.switch_button.SwitchButtonIcon
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import subot.core.ui.generated.resources.Res
import subot.core.ui.generated.resources.get_started
import subot.core.ui.generated.resources.next
import subot.core.ui.generated.resources.onboarding_description_1
import subot.core.ui.generated.resources.onboarding_description_2
import subot.core.ui.generated.resources.onboarding_description_3
import subot.core.ui.generated.resources.skip
import subot.core.ui.generated.resources.smart_scheduling
import subot.core.ui.generated.resources.stay_notified
import subot.core.ui.generated.resources.switch_to_dark_mode
import subot.core.ui.generated.resources.switch_to_light_mode
import subot.core.ui.generated.resources.welcome_to_subot

data class OnboardingPage(
    val icon: ImageVector,
    val title: StringResource,
    val description: StringResource
)

private val onboardingPages = listOf(
    OnboardingPage(
        icon = Icons.Default.SmartToy,
        title = Res.string.welcome_to_subot,
        description = Res.string.onboarding_description_1
    ),
    OnboardingPage(
        icon = Icons.Default.CalendarMonth,
        title = Res.string.smart_scheduling,
        description = Res.string.onboarding_description_2
    ),
    OnboardingPage(
        icon = Icons.Default.Notifications,
        title = Res.string.stay_notified,
        description = Res.string.onboarding_description_3
    )
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onOnboardingComplete: () -> Unit
) {

    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val coroutineScope = rememberCoroutineScope()
    val viewModel = koinViewModel<OnboardingViewModel>()
    
    val state by viewModel.state.collectAsStateWithLifecycle()
    val themeState = LocalThemeIsDark.current

    LaunchedEffect(state.isDarkMode) {
        state.isDarkMode?.let {
            themeState.value = it
        }
    }
    
    LaunchedEffect(state.isOnboardingCompleted) {
        if (state.isOnboardingCompleted) {
            onOnboardingComplete()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    AnimatedVisibility(
                        visible = pagerState.currentPage < onboardingPages.size - 1,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        AppTextButton(
                            text = stringResource(Res.string.skip),
                            onClick = {
                                viewModel.onEvent(OnboardingEvent.CompleteOnboarding)
                            },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                },
                navigationIcon = {
                    Box(modifier = Modifier.padding(start = 16.dp)) {
                        val isDark = state.isDarkMode ?: themeState.value
                        SwitchButton(
                            isSelected = isDark,
                            onStateChange = { viewModel.onEvent(OnboardingEvent.ToggleDarkMode(it)) },
                            icon = {
                                SwitchButtonIcon(
                                    isSelected = isDark,
                                    selectedIcon = Icons.Filled.WbSunny,
                                    iconColor = BluePrimary,
                                    unSelectedIcon = Icons.Filled.DarkMode,
                                    contentDescription = if (isDark) stringResource(Res.string.switch_to_light_mode) else stringResource(
                                        Res.string.switch_to_dark_mode
                                    )
                                )
                            },
                            switchButtonConfig = SwitchButtonConfig(
                                selectedBackgroundColor = MaterialTheme.colorScheme.primary,
//                                innerBoxColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Pager content
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) { page ->
                    OnboardingPageContent(
                        page = onboardingPages[page]
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Page indicator dots with animation
                WaterDropIndicator(
                    pagerState = pagerState,
                    modifier = Modifier.padding(16.dp),
                    activeColor = MaterialTheme.colorScheme.primary,
                    inactiveColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Navigation buttons
                if (pagerState.currentPage == onboardingPages.size - 1) {
                    AppPrimaryButton(
                        text = stringResource(Res.string.get_started),
                        onClick = {
                             viewModel.onEvent(OnboardingEvent.CompleteOnboarding)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    AppPrimaryButton(
                        text = stringResource(Res.string.next),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

}

@Composable
private fun OnboardingPageContent(
    page: OnboardingPage
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.extraLarge
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = page.icon,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource(page.title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(page.description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}