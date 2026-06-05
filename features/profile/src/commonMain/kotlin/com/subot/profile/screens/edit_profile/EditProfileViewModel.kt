package com.subot.profile.screens.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.domain.result.ApiResult
import com.subot.core.domain.usecase.GetProfileUseCase
import com.subot.core.domain.usecase.UpdateMemberProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val updateMemberProfileUseCase: UpdateMemberProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()

    private val validator = EditProfileValidator()

    init {
        loadCurrentProfile()
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.Refresh -> loadCurrentProfile()
            is EditProfileEvent.NameChanged -> updateName(event.name)
            is EditProfileEvent.TempatLahirChanged -> _uiState.update { it.copy(tempatLahir = event.tempatLahir) }
            is EditProfileEvent.TanggalLahirChanged -> updateDate(event.tanggalLahir)
            is EditProfileEvent.KelasChanged -> _uiState.update { it.copy(kelas = event.kelas) }
            is EditProfileEvent.AlamatChanged -> _uiState.update { it.copy(alamat = event.alamat) }
            is EditProfileEvent.TelephoneChanged -> updatePhone(event.telephone)
            is EditProfileEvent.NamaOrtuChanged -> _uiState.update { it.copy(namaOrtu = event.namaOrtu) }
            is EditProfileEvent.WorkOrtuChanged -> _uiState.update { it.copy(workOrtu = event.workOrtu) }
            is EditProfileEvent.FotoProfileChanged -> _uiState.update { 
                it.copy(
                    fotoProfileBytes = event.bytes,
                    fotoProfileName = event.fileName
                )
            }
            is EditProfileEvent.Submit -> submit()
            is EditProfileEvent.ClearError -> _uiState.update { it.copy(error = null) }
            is EditProfileEvent.ClearSuccess -> _uiState.update { it.copy(successMessage = null) }
        }
    }

    private fun loadCurrentProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isInitialLoading = true, error = null) }
            when (val result = getProfileUseCase()) {
                is ApiResult.Success -> {
                    val profile = result.data.profile
                    _uiState.update {
                        it.copy(
                            isInitialLoading = false,
                            namaLengkap = profile.namaLengkap.orEmpty(),
                            tempatLahir = profile.tempatLahir.orEmpty(),
                            tanggalLahir = profile.tanggalLahir.orEmpty(),
                            kelas = profile.kelas.orEmpty(),
                            alamat = profile.alamat.orEmpty(),
                            telephone = profile.telephone.orEmpty(),
                            namaOrtu = profile.namaOrtu.orEmpty(),
                            workOrtu = profile.workOrtu.orEmpty(),
                            profileImageUrl = profile.profileImageUrl,
                            error = null
                        )
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isInitialLoading = false,
                            error = result.message
                        )
                    }
                }
                is ApiResult.Loading -> Unit
            }
        }
    }

    private fun updateName(name: String) {
        val error = validator.validateName(name)
        _uiState.update { 
            it.copy(
                namaLengkap = name,
                nameError = error
            )
        }
    }

    private fun updatePhone(phone: String) {
        val error = validator.validatePhone(phone)
        _uiState.update { 
            it.copy(
                telephone = phone,
                phoneError = error
            )
        }
    }

    private fun updateDate(date: String) {
        val error = validator.validateDate(date)
        _uiState.update { 
            it.copy(
                tanggalLahir = date,
                dateError = error
            )
        }
    }

    private fun submit() {
        val state = _uiState.value

        if (state.isInitialLoading || state.isLoading) return
        
        // Validate fields
        val nameError = validator.validateName(state.namaLengkap)
        val phoneError = validator.validatePhone(state.telephone)
        val dateError = validator.validateDate(state.tanggalLahir)

        if (nameError != null || phoneError != null || dateError != null) {
            _uiState.update { 
                it.copy(
                    nameError = nameError,
                    phoneError = phoneError,
                    dateError = dateError
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, successMessage = null) }
            val result = updateMemberProfileUseCase(
                namaLengkap = state.namaLengkap,
                tempatLahir = state.tempatLahir.takeIf { it.isNotBlank() },
                tanggalLahir = state.tanggalLahir.takeIf { it.isNotBlank() },
                kelas = state.kelas.takeIf { it.isNotBlank() },
                alamat = state.alamat.takeIf { it.isNotBlank() },
                telephone = state.telephone.takeIf { it.isNotBlank() },
                namaOrtu = state.namaOrtu.takeIf { it.isNotBlank() },
                workOrtu = state.workOrtu.takeIf { it.isNotBlank() },
                fotoProfile = state.fotoProfileBytes,
                fotoProfileName = state.fotoProfileName
            )

            when (result) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            namaLengkap = result.data.namaLengkap.orEmpty(),
                            tempatLahir = result.data.tempatLahir.orEmpty(),
                            tanggalLahir = result.data.tanggalLahir.orEmpty(),
                            kelas = result.data.kelas.orEmpty(),
                            alamat = result.data.alamat.orEmpty(),
                            telephone = result.data.telephone.orEmpty(),
                            namaOrtu = result.data.namaOrtu.orEmpty(),
                            workOrtu = result.data.workOrtu.orEmpty(),
                            profileImageUrl = result.data.profileImageUrl,
                            successMessage = "Profil berhasil diperbarui",
                            error = null
                        )
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message,
                            successMessage = null
                        )
                    }
                }
                is ApiResult.Loading -> Unit
            }
        }
    }
}
