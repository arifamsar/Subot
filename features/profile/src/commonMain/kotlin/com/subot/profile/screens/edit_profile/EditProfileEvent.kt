package com.subot.profile.screens.edit_profile

sealed class EditProfileEvent {
    data object Refresh : EditProfileEvent()
    data class NameChanged(val name: String) : EditProfileEvent()
    data class TempatLahirChanged(val tempatLahir: String) : EditProfileEvent()
    data class TanggalLahirChanged(val tanggalLahir: String) : EditProfileEvent()
    data class KelasChanged(val kelas: String) : EditProfileEvent()
    data class AlamatChanged(val alamat: String) : EditProfileEvent()
    data class TelephoneChanged(val telephone: String) : EditProfileEvent()
    data class NamaOrtuChanged(val namaOrtu: String) : EditProfileEvent()
    data class WorkOrtuChanged(val workOrtu: String) : EditProfileEvent()
    data class FotoProfileChanged(val bytes: ByteArray?, val fileName: String?) : EditProfileEvent()
    data object Submit : EditProfileEvent()
    data object ClearError : EditProfileEvent()
    data object ClearSuccess : EditProfileEvent()
}
