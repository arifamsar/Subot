package com.subot.profile.screens.edit_profile

data class EditProfileUiState(
    val namaLengkap: String = "",
    val tempatLahir: String = "",
    val tanggalLahir: String = "",
    val kelas: String = "",
    val alamat: String = "",
    val telephone: String = "",
    val namaOrtu: String = "",
    val workOrtu: String = "",
    val fotoProfileBytes: ByteArray? = null,
    val fotoProfileName: String? = null,
    val profileImageUrl: String? = null,
    val isInitialLoading: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val nameError: String? = null,
    val phoneError: String? = null,
    val dateError: String? = null
)
