package com.sukarobot.subot

import androidx.compose.runtime.Composable
import com.sukarobot.subot.navigation.AppNavHost
import com.sukarobot.subot.theme.AppTheme

@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    AppNavHost()
}
