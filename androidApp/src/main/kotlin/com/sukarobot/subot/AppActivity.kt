package com.sukarobot.subot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.subot.core.data.service.initPreferencesDataStore

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPreferencesDataStore(applicationContext)
        enableEdgeToEdge()
        setContent { 
            App(
                onThemeChanged = { isDark ->
                    val view = LocalView.current
                    if (!view.isInEditMode) {
                        SideEffect {
                            val window = this@AppActivity.window
                            val insetsController = WindowCompat.getInsetsController(window, view)
                            insetsController.isAppearanceLightStatusBars = !isDark
                            insetsController.isAppearanceLightNavigationBars = !isDark
                        }
                    }
                }
            )
        }
    }
}

