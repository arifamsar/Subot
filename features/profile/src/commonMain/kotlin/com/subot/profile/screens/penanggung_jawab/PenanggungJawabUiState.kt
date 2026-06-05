package com.subot.profile.screens.penanggung_jawab

data class PenanggungJawabUiState(
    val namaPenanggungJawab: String = "",
    val emailPenanggungJawab: String = "",
    val telephonePenanggungJawab: String = "",
    val isInitialLoading: Boolean = false,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val nameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null
)
