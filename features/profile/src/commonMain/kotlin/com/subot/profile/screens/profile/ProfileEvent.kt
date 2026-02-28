package com.subot.profile.screens.profile

sealed class ProfileEvent {
    data class ToggleDarkMode(val enabled: Boolean) : ProfileEvent()
    data class SetLanguage(val languageCode: String) : ProfileEvent()
    data object Logout : ProfileEvent()
    data object ClearLogoutError : ProfileEvent()
}

