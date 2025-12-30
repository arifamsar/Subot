package com.sukarobot.subot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.subot.core.data.di.preferencesModule
import com.subot.core.data.service.UserPreferences
import com.sukarobot.subot.navigation.AppNavHost
import com.sukarobot.subot.theme.AppTheme
import com.sukarobot.subot.ui.di.viewModelModule
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) {
    KoinApplication(
        application = {
            modules(preferencesModule, viewModelModule)
        }
    ) {
        val userPreferences = koinInject<UserPreferences>()
        val isDark by userPreferences.darkModeEnabledFlow().collectAsState(initial = false)

        AppTheme(isDark = isDark, onThemeChanged = onThemeChanged) {
            AppNavHost()
        }
    }
}
