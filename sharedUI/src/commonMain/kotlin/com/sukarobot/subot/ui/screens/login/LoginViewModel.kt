package com.sukarobot.subot.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    init {
        loadSchools()
    }

    /**
     * Sets the initial login type from the navigation route parameter.
     * Called once when the screen is first composed.
     */
    fun setInitialLoginType(loginType: LoginType) {
        _uiState.update { it.copy(loginType = loginType) }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginTypeChanged -> handleLoginTypeChanged(event.loginType)
            is LoginEvent.SchoolSelected -> handleSchoolSelected(event.school)
            is LoginEvent.ToggleSchoolDropdown -> handleToggleSchoolDropdown()
            is LoginEvent.UniqueIdChanged -> handleUniqueIdChanged(event.uniqueId)
            is LoginEvent.PasswordChanged -> handlePasswordChanged(event.password)
            is LoginEvent.ValidateAndLogin -> handleValidateAndLogin()
            is LoginEvent.ClearLoginSuccess -> handleClearLoginSuccess()
            is LoginEvent.ClearLoginError -> handleClearLoginError()
            is LoginEvent.ForgotPassword -> handleForgotPassword()
        }
    }

    private fun loadSchools() {
        // TODO: Replace with repository call to fetch schools from API
        val schools = listOf(
            School(id = "1", name = "SD Negeri 1 Jakarta"),
            School(id = "2", name = "SD Negeri 2 Bandung"),
            School(id = "3", name = "SMP Negeri 1 Surabaya"),
            School(id = "4", name = "SMP Negeri 3 Yogyakarta"),
            School(id = "5", name = "SMA Negeri 1 Semarang"),
            School(id = "6", name = "SMA Negeri 2 Medan"),
        )
        _uiState.update { it.copy(schools = schools) }
    }

    private fun handleLoginTypeChanged(loginType: LoginType) {
        _uiState.update {
            it.copy(
                loginType = loginType,
                // Reset form fields and errors when switching modes
                selectedSchool = null,
                schoolError = null,
                isSchoolDropdownExpanded = false,
                uniqueId = "",
                uniqueIdError = null,
                password = "",
                passwordError = null,
                loginError = null
            )
        }
    }

    private fun handleSchoolSelected(school: School) {
        _uiState.update {
            it.copy(
                selectedSchool = school,
                schoolError = null,
                isSchoolDropdownExpanded = false,
                loginError = null
            )
        }
    }

    private fun handleToggleSchoolDropdown() {
        _uiState.update {
            it.copy(isSchoolDropdownExpanded = !it.isSchoolDropdownExpanded)
        }
    }

    private fun handleUniqueIdChanged(uniqueId: String) {
        _uiState.update {
            it.copy(
                uniqueId = uniqueId,
                uniqueIdError = null,
                loginError = null
            )
        }
    }

    private fun handlePasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordError = null,
                loginError = null
            )
        }
    }

    private fun handleValidateAndLogin() {
        val currentState = _uiState.value
        val passwordValidation = LoginValidator.validatePassword(currentState.password)
        val passwordError = passwordValidation.errorMessage?.let { ValidationError.valueOf(it) }

        when (currentState.loginType) {
            LoginType.MITRA -> {
                val schoolValidation = LoginValidator.validateSchoolSelection(currentState.selectedSchool)
                val schoolError = schoolValidation.errorMessage?.let { ValidationError.valueOf(it) }

                _uiState.update {
                    it.copy(
                        schoolError = schoolError,
                        passwordError = passwordError
                    )
                }

                if (schoolValidation.isValid && passwordValidation.isValid) {
                    performLogin()
                }
            }

            LoginType.MEMBER -> {
                val uniqueIdValidation = LoginValidator.validateUniqueId(currentState.uniqueId)
                val uniqueIdError = uniqueIdValidation.errorMessage?.let { ValidationError.valueOf(it) }

                _uiState.update {
                    it.copy(
                        uniqueIdError = uniqueIdError,
                        passwordError = passwordError
                    )
                }

                if (uniqueIdValidation.isValid && passwordValidation.isValid) {
                    performLogin()
                }
            }
        }
    }

    private fun performLogin() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, loginError = null) }

            try {
                // Simulate network delay
                delay(1500)

                // For demo purposes, accept any valid credentials
                // In real app, this would call a repository/use case
                userPreferences.setLoggedIn(true)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoginSuccessful = true,
                        password = ""
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        loginError = e.message ?: "Login failed. Please try again."
                    )
                }
            }
        }
    }

    private fun handleClearLoginSuccess() {
        _uiState.update { it.copy(isLoginSuccessful = false) }
    }

    private fun handleClearLoginError() {
        _uiState.update { it.copy(loginError = null) }
    }

    private fun handleForgotPassword() {
        // Handle forgot password logic here
    }
}
