package com.subot.profile.screens.change_password

data class ChangePasswordUiState(
    val currentPassword: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val currentPasswordError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null
)
