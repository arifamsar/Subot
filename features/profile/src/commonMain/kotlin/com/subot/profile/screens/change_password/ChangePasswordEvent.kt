package com.subot.profile.screens.change_password

sealed class ChangePasswordEvent {
    data class CurrentPasswordChanged(val currentPassword: String) : ChangePasswordEvent()
    data class PasswordChanged(val password: String) : ChangePasswordEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : ChangePasswordEvent()
    data object Submit : ChangePasswordEvent()
    data object ClearError : ChangePasswordEvent()
    data object ClearSuccess : ChangePasswordEvent()
}
