package com.sukarobot.subot

import androidx.compose.runtime.Composable
import com.subot.core.data.di.preferencesModule
import com.sukarobot.subot.navigation.AppNavHost
import com.sukarobot.subot.theme.AppTheme
import com.sukarobot.subot.ui.di.viewModelModule
import org.koin.compose.KoinApplication

@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    KoinApplication(
        application = {
            modules(preferencesModule, viewModelModule)
        }
    ) {
        AppNavHost()
    }
}
