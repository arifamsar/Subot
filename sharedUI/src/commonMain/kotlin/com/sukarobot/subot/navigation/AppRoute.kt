package com.sukarobot.subot.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Type-safe navigation routes for the app using Nav3
 */
sealed interface AppRoute : NavKey {
    
    /**
     * Marker interface for routes that require user authentication
     */
    interface RequiresLogin

    // Auth Flow
    @Serializable
    data object Splash : AppRoute
    
    @Serializable
    data object Onboarding : AppRoute
    
    @Serializable
    data object Login : AppRoute
    
    // Main Flow (with Bottom Navigation) - These require login
    @Serializable
    data object Main : AppRoute, RequiresLogin

    @Serializable
    data object Home : AppRoute, RequiresLogin

    @Serializable
    data object Schedule : AppRoute, RequiresLogin

    @Serializable
    data object Transaction : AppRoute, RequiresLogin

    @Serializable
    data object Profile : AppRoute, RequiresLogin

    // Nested Settings Flow (inside Profile) - These also require login
    @Serializable
    data object Settings : AppRoute, RequiresLogin

    @Serializable
    data class SettingsDetail(val settingId: String) : AppRoute, RequiresLogin
}
