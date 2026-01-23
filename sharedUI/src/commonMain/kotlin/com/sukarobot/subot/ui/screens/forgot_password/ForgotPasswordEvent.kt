package com.sukarobot.subot.ui.screens.forgot_password

sealed class ForgotPasswordEvent {
    data class PhoneNumberChanged(val phoneNumber: String) : ForgotPasswordEvent()
    data object Submit : ForgotPasswordEvent()
    data object Back : ForgotPasswordEvent()
    data object ClearError : ForgotPasswordEvent()
    data object ClearSuccess : ForgotPasswordEvent()
}
