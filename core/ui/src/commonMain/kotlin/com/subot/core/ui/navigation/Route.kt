package com.subot.core.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object Splash : Route

    @Serializable
    data object Onboarding : Route

    @Serializable
    data object Portal : Route

    @Serializable
    data class Login(val loginType: String = "MITRA") : Route

    // Main Flow (with Bottom Navigation) - These require login
    @Serializable
    data object Main : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object Schedule : Route

    @Serializable
    data object Transaction : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data object Settings : Route

    @Serializable
    data class SettingsDetail(val settingId: String) : Route

    @Serializable
    data object ForgetPassword : Route
}