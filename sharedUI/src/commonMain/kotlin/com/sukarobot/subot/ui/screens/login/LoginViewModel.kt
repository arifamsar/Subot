package com.sukarobot.subot.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null,
            loginError = null
        )
    }
    
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = null,
            loginError = null
        )
    }
    
    fun validateAndLogin() {
        val currentState = _uiState.value
        
        // Validate email
        val emailValidation = LoginValidator.validateEmail(currentState.email)
        val passwordValidation = LoginValidator.validatePassword(currentState.password)
        
        _uiState.value = currentState.copy(
            emailError = emailValidation.errorMessage,
            passwordError = passwordValidation.errorMessage
        )
        
        // If validation passes, attempt login
        if (emailValidation.isValid && passwordValidation.isValid) {
            performLogin()
        }
    }
    
    private fun performLogin() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, loginError = null)
            
            try {
                // Simulate network delay
                delay(1500)
                
                // For demo purposes, accept any valid credentials
                // In real app, this would call a repository/use case
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoginSuccessful = true
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    loginError = "Login failed. Please try again."
                )
            }
        }
    }
    
    fun clearLoginSuccess() {
        _uiState.value = _uiState.value.copy(isLoginSuccessful = false)
    }
}
