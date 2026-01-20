package com.sukarobot.subot.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import subot.sharedui.generated.resources.Res
import subot.sharedui.generated.resources.logo

@Composable
fun SplashScreen(
    uiState: SplashUiState = SplashUiState(),
    onEvent: (SplashEvent) -> Unit = {},
    onNavigateToHome: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }
    var splashDelayFinished by retain { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // Load initial statuses
        onEvent(SplashEvent.LoadStatuses)

        // Scale animation
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
        )

        // Fade in animation
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500
            )
        )

        // Wait before navigating
        delay(1500)
        splashDelayFinished = true
    }

    LaunchedEffect(uiState.isReady, splashDelayFinished) {
        if (uiState.isReady && splashDelayFinished) {
            when (uiState.nextScreen) {
                NextScreen.Home -> onNavigateToHome()
                NextScreen.Login -> onNavigateToLogin()
                NextScreen.Onboarding -> onNavigateToOnboarding()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.logo),
                contentDescription = "Subot Logo",
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale.value)
            )
        }
    }
}
