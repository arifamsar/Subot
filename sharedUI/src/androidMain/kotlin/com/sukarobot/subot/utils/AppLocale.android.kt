package com.sukarobot.subot.utils

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.ui.platform.LocalConfiguration
import java.util.Locale

actual object LocalAppLocale {

    private var default: Locale? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        val config = LocalConfiguration.current

        if (default == null) {
            default = Locale.getDefault()
        }

        val new = when (value) {
            null -> default ?: Locale.getDefault()
            else -> Locale.forLanguageTag(value)
        }

        Locale.setDefault(new)

        val newConfig = Configuration(config).apply {
            setLocale(new)
        }

        return LocalConfiguration.provides(newConfig)
    }
}