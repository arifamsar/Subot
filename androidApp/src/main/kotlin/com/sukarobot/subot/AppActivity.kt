package com.sukarobot.subot

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.subot.core.data.service.initPreferencesDataStore

class AppActivity : ComponentActivity() {

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            // Permission result is handled silently — the app functions
            // whether the user grants or denies notification permission.
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPreferencesDataStore(applicationContext)
        com.subot.core.data.database.initDatabaseContext(applicationContext)
        requestNotificationPermissionIfNeeded()
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

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val isGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!isGranted) {
                requestNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}


