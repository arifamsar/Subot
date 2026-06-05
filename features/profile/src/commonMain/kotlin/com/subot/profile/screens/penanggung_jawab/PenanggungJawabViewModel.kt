package com.subot.profile.screens.penanggung_jawab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.domain.result.ApiResult
import com.subot.core.domain.usecase.GetProfileUseCase
import com.subot.core.domain.usecase.UpdatePenanggungJawabUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PenanggungJawabViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val updatePenanggungJawabUseCase: UpdatePenanggungJawabUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PenanggungJawabUiState())
    val uiState: StateFlow<PenanggungJawabUiState> = _uiState.asStateFlow()

    private val validator = PenanggungJawabValidator()

    init {
        loadCurrentProfile()
    }

    fun onEvent(event: PenanggungJawabEvent) {
        when (event) {
            is PenanggungJawabEvent.Refresh -> loadCurrentProfile(isRefresh = true)
            is PenanggungJawabEvent.NameChanged -> updateName(event.name)
            is PenanggungJawabEvent.EmailChanged -> updateEmail(event.email)
            is PenanggungJawabEvent.PhoneChanged -> updatePhone(event.phone)
            is PenanggungJawabEvent.Submit -> submit()
            is PenanggungJawabEvent.ClearError -> _uiState.update { it.copy(error = null) }
            is PenanggungJawabEvent.ClearSuccess -> _uiState.update { it.copy(successMessage = null) }
        }
    }

    private fun loadCurrentProfile(isRefresh: Boolean = false) {
        viewModelScope.launch {
            if (isRefresh) {
                _uiState.update { it.copy(isRefreshing = true, error = null) }
            } else {
                _uiState.update { it.copy(isInitialLoading = true, error = null) }
            }
            when (val result = getProfileUseCase()) {
                is ApiResult.Success -> {
                    val profile = result.data.profile
                    _uiState.update {
                        it.copy(
                            isInitialLoading = false,
                            isRefreshing = false,
                            namaPenanggungJawab = profile.namaPenanggungJawab.orEmpty(),
                            emailPenanggungJawab = profile.emailPenanggungJawab
                                ?: profile.email.orEmpty(),
                            telephonePenanggungJawab = profile.telephonePenanggungJawab.orEmpty(),
                            error = null
                        )
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isInitialLoading = false,
                            isRefreshing = false,
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
                namaPenanggungJawab = name,
                nameError = error
            )
        }
    }

    private fun updateEmail(email: String) {
        val error = validator.validateEmail(email)
        _uiState.update { 
            it.copy(
                emailPenanggungJawab = email,
                emailError = error
            )
        }
    }

    private fun updatePhone(phone: String) {
        val error = validator.validatePhone(phone)
        _uiState.update { 
            it.copy(
                telephonePenanggungJawab = phone,
                phoneError = error
            )
        }
    }

    private fun submit() {
        val state = _uiState.value

        if (state.isInitialLoading || state.isLoading) return
        
        // Validate all fields
        val nameError = validator.validateName(state.namaPenanggungJawab)
        val emailError = validator.validateEmail(state.emailPenanggungJawab)
        val phoneError = validator.validatePhone(state.telephonePenanggungJawab)

        if (nameError != null || emailError != null || phoneError != null) {
            _uiState.update { 
                it.copy(
                    nameError = nameError,
                    emailError = emailError,
                    phoneError = phoneError
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, successMessage = null) }
            val result = updatePenanggungJawabUseCase(
                namaPenanggungJawab = state.namaPenanggungJawab,
                emailPenanggungJawab = state.emailPenanggungJawab,
                telephonePenanggungJawab = state.telephonePenanggungJawab
            )

            when (result) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            namaPenanggungJawab = result.data.namaPenanggungJawab,
                            emailPenanggungJawab = result.data.emailPenanggungJawab,
                            telephonePenanggungJawab = result.data.telephonePenanggungJawab,
                            successMessage = "Data berhasil diperbarui",
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
