package com.sukarobot.subot.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> handleEmailChanged(event.email)
            is LoginEvent.PasswordChanged -> handlePasswordChanged(event.password)
            is LoginEvent.ValidateAndLogin -> handleValidateAndLogin()
            is LoginEvent.ClearLoginSuccess -> handleClearLoginSuccess()
            is LoginEvent.ClearLoginError -> handleClearLoginError()
            is LoginEvent.ForgotPassword -> handleForgotPassword()
        }
    }

    private fun handleEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null,
            loginError = null
        )
    }

    private fun handlePasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = null,
            loginError = null
        )
    }

    private fun handleValidateAndLogin() {
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

            // Clear form
            _uiState.value = _uiState.value.copy(
                email = "",
                password = ""
            )
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
                userPreferences.setLoggedIn(true)
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

    private fun handleClearLoginSuccess() {
        _uiState.value = _uiState.value.copy(isLoginSuccessful = false)
    }

    private fun handleClearLoginError() {
        _uiState.value = _uiState.value.copy(loginError = null)
    }

    private fun handleForgotPassword() {
        // Handle forgot password logic here
        // For now, we'll just log it or show a message
    }
}
