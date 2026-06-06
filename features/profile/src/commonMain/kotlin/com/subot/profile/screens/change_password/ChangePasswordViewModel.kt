package com.subot.profile.screens.change_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.domain.result.ApiResult
import com.subot.core.domain.usecase.ChangePasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChangePasswordViewModel(
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChangePasswordUiState())
    val uiState: StateFlow<ChangePasswordUiState> = _uiState.asStateFlow()

    private val validator = ChangePasswordValidator()

    fun onEvent(event: ChangePasswordEvent) {
        when (event) {
            is ChangePasswordEvent.CurrentPasswordChanged -> updateCurrentPassword(event.currentPassword)
            is ChangePasswordEvent.PasswordChanged -> updatePassword(event.password)
            is ChangePasswordEvent.ConfirmPasswordChanged -> updateConfirmPassword(event.confirmPassword)
            is ChangePasswordEvent.Submit -> submit()
            is ChangePasswordEvent.ClearError -> _uiState.update { it.copy(error = null) }
            is ChangePasswordEvent.ClearSuccess -> _uiState.update { it.copy(successMessage = null) }
        }
    }

    private fun updateCurrentPassword(value: String) {
        val error = validator.validateCurrentPassword(value)
        _uiState.update {
            it.copy(
                currentPassword = value,
                currentPasswordError = error
            )
        }
    }

    private fun updatePassword(value: String) {
        val error = validator.validateNewPassword(value)
        _uiState.update {
            it.copy(
                password = value,
                passwordError = error
            )
        }
        // Re-validate confirm password if it's not empty
        if (_uiState.value.confirmPassword.isNotEmpty()) {
            val confirmError = validator.validateConfirmPassword(_uiState.value.confirmPassword, value)
            _uiState.update {
                it.copy(confirmPasswordError = confirmError)
            }
        }
    }

    private fun updateConfirmPassword(value: String) {
        val error = validator.validateConfirmPassword(value, _uiState.value.password)
        _uiState.update {
            it.copy(
                confirmPassword = value,
                confirmPasswordError = error
            )
        }
    }

    private fun submit() {
        val state = _uiState.value
        if (state.isLoading) return

        val currentError = validator.validateCurrentPassword(state.currentPassword)
        val passwordError = validator.validateNewPassword(state.password)
        val confirmError = validator.validateConfirmPassword(state.confirmPassword, state.password)

        if (currentError != null || passwordError != null || confirmError != null) {
            _uiState.update {
                it.copy(
                    currentPasswordError = currentError,
                    passwordError = passwordError,
                    confirmPasswordError = confirmError
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, successMessage = null) }
            when (val result = changePasswordUseCase(
                currentPassword = state.currentPassword,
                password = state.password,
                passwordConfirmation = state.confirmPassword
            )) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            currentPassword = "",
                            password = "",
                            confirmPassword = "",
                            successMessage = result.data,
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
