package com.sukarobot.subot.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
import com.sukarobot.subot.utils.LocalAppLocale

@Composable
fun Localization(selectedLanguage: String, content: @Composable () -> Unit) {

    CompositionLocalProvider(
        LocalAppLocale provides selectedLanguage,
    ) {
        key(selectedLanguage) {
            content()
        }
    }
}