package com.sukarobot.subot.ui.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.subot.core.ui.components.AppPrimaryButton
import com.subot.core.ui.components.AppTextButton
import com.subot.core.ui.components.WaterDropIndicator
import com.subot.core.ui.theme.LocalThemeIsDark
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import subot.core.ui.generated.resources.Res
import subot.core.ui.generated.resources.get_started
import subot.core.ui.generated.resources.maskot
import subot.core.ui.generated.resources.next
import subot.core.ui.generated.resources.onboarding_description_1
import subot.core.ui.generated.resources.onboarding_description_2
import subot.core.ui.generated.resources.onboarding_description_3
import subot.core.ui.generated.resources.skip
import subot.core.ui.generated.resources.smart_scheduling
import subot.core.ui.generated.resources.stay_notified
import subot.core.ui.generated.resources.welcome_to_subot

data class OnboardingPage(
    val icon: ImageVector? = null,
    val image: DrawableResource? = null,
    val title: StringResource,
    val description: StringResource
)

private val onboardingPages = listOf(
    OnboardingPage(
        image = Res.drawable.maskot,
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

private val WIDTH_DP_MEDIUM_LOWER_BOUND = 600.dp
private val HEIGHT_DP_MEDIUM_LOWER_BOUND = 480.dp

private data class OnboardingLayoutSpec(
    val useTabletLayout: Boolean,
    val contentMaxWidth: Dp,
    val contentHorizontalPadding: Dp,
    val contentVerticalPadding: Dp,
    val indicatorTopSpacing: Dp,
    val buttonTopSpacing: Dp,
    val bottomSpacing: Dp,
    val buttonMaxWidth: Dp,
    val visualContainerSize: Dp,
    val imageSize: Dp,
    val iconSize: Dp,
    val descriptionHorizontalPadding: Dp
)

private fun onboardingLayoutSpec(screenWidth: Dp, screenHeight: Dp): OnboardingLayoutSpec {
    val useTabletLayout =
        screenWidth >= WIDTH_DP_MEDIUM_LOWER_BOUND && screenHeight >= HEIGHT_DP_MEDIUM_LOWER_BOUND

    return if (useTabletLayout) {
        OnboardingLayoutSpec(
            useTabletLayout = true,
            contentMaxWidth = 920.dp,
            contentHorizontalPadding = 40.dp,
            contentVerticalPadding = 32.dp,
            indicatorTopSpacing = 24.dp,
            buttonTopSpacing = 36.dp,
            bottomSpacing = 24.dp,
            buttonMaxWidth = 420.dp,
            visualContainerSize = 240.dp,
            imageSize = 190.dp,
            iconSize = 120.dp,
            descriptionHorizontalPadding = 0.dp
        )
    } else {
        OnboardingLayoutSpec(
            useTabletLayout = false,
            contentMaxWidth = 600.dp,
            contentHorizontalPadding = 24.dp,
            contentVerticalPadding = 24.dp,
            indicatorTopSpacing = 32.dp,
            buttonTopSpacing = 48.dp,
            bottomSpacing = 24.dp,
            buttonMaxWidth = 560.dp,
            visualContainerSize = 200.dp,
            imageSize = 160.dp,
            iconSize = 100.dp,
            descriptionHorizontalPadding = 24.dp
        )
    }
}

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
                }
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            val layoutSpec = onboardingLayoutSpec(
                screenWidth = maxWidth,
                screenHeight = maxHeight
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .widthIn(max = layoutSpec.contentMaxWidth)
                        .padding(
                            horizontal = layoutSpec.contentHorizontalPadding,
                            vertical = layoutSpec.contentVerticalPadding
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) { page ->
                        OnboardingPageContent(
                            page = onboardingPages[page],
                            layoutSpec = layoutSpec
                        )
                    }

                    Spacer(modifier = Modifier.height(layoutSpec.indicatorTopSpacing))

                    WaterDropIndicator(
                        pagerState = pagerState,
                        modifier = Modifier.padding(16.dp),
                        activeColor = MaterialTheme.colorScheme.primary,
                        inactiveColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    )

                    Spacer(modifier = Modifier.height(layoutSpec.buttonTopSpacing))

                    if (pagerState.currentPage == onboardingPages.size - 1) {
                        AppPrimaryButton(
                            text = stringResource(Res.string.get_started),
                            onClick = {
                                viewModel.onEvent(OnboardingEvent.CompleteOnboarding)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = layoutSpec.buttonMaxWidth)
                        )
                    } else {
                        AppPrimaryButton(
                            text = stringResource(Res.string.next),
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = layoutSpec.buttonMaxWidth)
                        )
                    }

                    Spacer(modifier = Modifier.height(layoutSpec.bottomSpacing))
                }
            }
        }
    }

}

@Composable
private fun OnboardingPageContent(
    page: OnboardingPage,
    layoutSpec: OnboardingLayoutSpec
) {
    if (layoutSpec.useTabletLayout) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                OnboardingVisual(page = page, layoutSpec = layoutSpec)
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(page.title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(page.description),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        return
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OnboardingVisual(page = page, layoutSpec = layoutSpec)

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
            modifier = Modifier.padding(horizontal = layoutSpec.descriptionHorizontalPadding)
        )
    }
}

@Composable
private fun OnboardingVisual(
    page: OnboardingPage,
    layoutSpec: OnboardingLayoutSpec
) {
    Box(
        modifier = Modifier
            .size(layoutSpec.visualContainerSize)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.extraLarge
            ),
        contentAlignment = Alignment.Center
    ) {
        if (page.image != null) {
            Image(
                painter = painterResource(page.image),
                contentDescription = null,
                modifier = Modifier.size(layoutSpec.imageSize)
            )
        } else if (page.icon != null) {
            Icon(
                imageVector = page.icon,
                contentDescription = null,
                modifier = Modifier.size(layoutSpec.iconSize),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}