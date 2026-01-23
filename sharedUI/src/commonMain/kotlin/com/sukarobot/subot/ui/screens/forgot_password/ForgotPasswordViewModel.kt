package com.sukarobot.subot.ui.screens.forgot_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForgotPasswordViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState.asStateFlow()

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.PhoneNumberChanged -> {
                _uiState.update { it.copy(phoneNumber = event.phoneNumber, error = null) }
            }
            ForgotPasswordEvent.Submit -> submit()
            ForgotPasswordEvent.Back -> { /* Handle navigation in screen */ }
            ForgotPasswordEvent.ClearError -> {
                _uiState.update { it.copy(error = null) }
            }
            ForgotPasswordEvent.ClearSuccess -> {
                 _uiState.update { it.copy(isSuccess = false) }
            }
        }
    }

    private fun submit() {
        val phone = _uiState.value.phoneNumber
        if (phone.isBlank()) {
            _uiState.update { it.copy(error = "Phone number is required") }
            return
        }
        
        // Basic validation: ensure it contains only digits and has a reasonable length
        if (!phone.all { it.isDigit() || it == '+' } || phone.length < 10) {
             _uiState.update { it.copy(error = "Invalid phone number") }
             return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // Simulate network delay
                delay(1500)
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = "Failed to send code. Please try again.") 
                }
            }
        }
    }
}
