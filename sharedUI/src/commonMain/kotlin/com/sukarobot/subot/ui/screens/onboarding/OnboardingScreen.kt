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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sukarobot.subot.theme.LocalThemeIsDark
import com.sukarobot.subot.ui.components.AppPrimaryButton
import com.sukarobot.subot.ui.components.AppTextButton
import com.sukarobot.subot.ui.components.WaterDropIndicator
import com.sukarobot.subot.ui.components.icons.Hicon
import com.sukarobot.subot.ui.components.icons.MoonOutlined
import com.sukarobot.subot.ui.components.icons.SunOutlined
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

data class OnboardingPage(
    val icon: ImageVector,
    val title: String,
    val description: String
)

private val onboardingPages = listOf(
    OnboardingPage(
        icon = Icons.Default.SmartToy,
        title = "Welcome to Subot",
        description = "Your intelligent assistant that helps you manage your daily tasks and activities with ease."
    ),
    OnboardingPage(
        icon = Icons.Default.CalendarMonth,
        title = "Smart Scheduling",
        description = "Organize your schedule effortlessly. Never miss an important appointment or deadline again."
    ),
    OnboardingPage(
        icon = Icons.Default.Notifications,
        title = "Stay Notified",
        description = "Get timely reminders and notifications to keep you on track throughout your day."
    )
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onOnboardingComplete: () -> Unit
) {

    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val coroutineScope = rememberCoroutineScope()
    val viewModel = koinViewModel<OnboardingViewModel>()
    val darkModeEnabled by viewModel.darkModeEnabled.collectAsState()
    val themeState = LocalThemeIsDark.current

    LaunchedEffect(darkModeEnabled) {
        themeState.value = darkModeEnabled
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
                            text = "Skip",
                            onClick = onOnboardingComplete,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.toggleDarkMode(!darkModeEnabled) }) {
                        Icon(
                            imageVector = if (darkModeEnabled) Hicon.SunOutlined else Hicon.MoonOutlined,
                            contentDescription = if (darkModeEnabled) "Switch to light mode" else "Switch to dark mode"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
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
                        text = "Get Started",
                        onClick = onOnboardingComplete,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    AppPrimaryButton(
                        text = "Next",
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
            text = page.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}
