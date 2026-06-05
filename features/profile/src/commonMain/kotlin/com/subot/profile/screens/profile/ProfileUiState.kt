package com.subot.profile.screens.profile

import com.subot.core.domain.AppLanguage
import com.subot.core.domain.model.UserProfile

data class ProfileUiState(
    val isProfileLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val profile: UserProfile? = null,
    val profileError: String? = null,
    val darkModeEnabled: Boolean = false,
    val selectedLanguage: String = AppLanguage.INDONESIAN.code,
    val isLoggingOut: Boolean = false,
    val isLogoutSuccessful: Boolean = false,
    val logoutError: String? = null,
    val userRole: String? = null
)
