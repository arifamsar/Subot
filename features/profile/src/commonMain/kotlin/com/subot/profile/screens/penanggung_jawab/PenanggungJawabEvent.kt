package com.subot.profile.screens.penanggung_jawab

sealed class PenanggungJawabEvent {
    data object Refresh : PenanggungJawabEvent()
    data class NameChanged(val name: String) : PenanggungJawabEvent()
    data class EmailChanged(val email: String) : PenanggungJawabEvent()
    data class PhoneChanged(val phone: String) : PenanggungJawabEvent()
    data object Submit : PenanggungJawabEvent()
    data object ClearError : PenanggungJawabEvent()
    data object ClearSuccess : PenanggungJawabEvent()
}
