package com.subot.profile.screens.profile

import com.subot.core.domain.AppLanguage

data class ProfileUiState(
    val darkModeEnabled: Boolean = false,
    val selectedLanguage: String = AppLanguage.INDONESIAN.code,
    val isLoggingOut: Boolean = false,
    val isLogoutSuccessful: Boolean = false,
    val logoutError: String? = null
)

