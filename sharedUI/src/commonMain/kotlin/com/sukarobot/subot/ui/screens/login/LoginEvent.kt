package com.sukarobot.subot.ui.screens.login

/**
 * Sealed class representing all possible UI events for the login screen
 */
sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object ValidateAndLogin : LoginEvent()
    object ClearLoginSuccess : LoginEvent()
    object ClearLoginError : LoginEvent()
    object ForgotPassword : LoginEvent()
}