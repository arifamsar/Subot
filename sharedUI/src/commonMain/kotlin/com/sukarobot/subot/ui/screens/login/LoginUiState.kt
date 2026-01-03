package com.sukarobot.subot.ui.screens.login

/**
 * UI state for the login screen following MVI pattern
 */
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val loginError: String? = null
) {
    /**
     * Check if the login form is valid (no errors)
     */
    val isFormValid: Boolean
        get() = emailError == null && passwordError == null && email.isNotEmpty() && password.isNotEmpty()
}