package com.sukarobot.subot.ui.screens.login

import com.subot.core.domain.model.School

/**
 * Sealed class representing all possible UI events for the login screen
 */
sealed class LoginEvent {
    // Login type switching
    data class LoginTypeChanged(val loginType: LoginType) : LoginEvent()

    // Mitra-specific events
    data class SchoolSelected(val school: School) : LoginEvent()
    data object ToggleSchoolDropdown : LoginEvent()

    // Member-specific events
    data class UniqueIdChanged(val uniqueId: String) : LoginEvent()

    // Shared events
    data class PasswordChanged(val password: String) : LoginEvent()
    data object ValidateAndLogin : LoginEvent()
    data object ClearLoginSuccess : LoginEvent()
    data object ClearLoginError : LoginEvent()
    data object ForgotPassword : LoginEvent()
}