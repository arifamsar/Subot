package com.sukarobot.subot

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.subot.core.data.di.dataModule
import com.subot.core.data.service.UserPreferences
import com.subot.core.ui.theme.AppTheme
import com.sukarobot.subot.navigation.AppNavigation
import com.sukarobot.subot.ui.di.viewModelModule
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) {
    KoinApplication(
        application = {
            modules(dataModule, viewModelModule)
        }
    ) {
        val appState: AppState = rememberAppState()
        val userPreferences = koinInject<UserPreferences>()
        val isDark by userPreferences.darkModeEnabledFlow().collectAsStateWithLifecycle(initialValue = null)

        // Only render when theme preference is loaded to prevent flash
        isDark?.let { darkMode ->
            AppTheme(isDark = darkMode, onThemeChanged = onThemeChanged) {
                Scaffold { innerPadding ->
                    AppNavigation(
                        appState = appState,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}