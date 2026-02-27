package com.sukarobot.subot.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.subot.core.domain.model.School
import com.subot.core.domain.repository.LoginAs
import com.subot.core.domain.result.ApiResult
import com.subot.core.domain.usecase.GetSchoolsPagedUseCase
import com.subot.core.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    getSchoolsPagedUseCase: GetSchoolsPagedUseCase
) : ViewModel() {

    /** Paged stream of schools — collected in the UI via collectAsLazyPagingItems(). */
    val schoolsPagingData = getSchoolsPagedUseCase(perPage = 20).cachedIn(viewModelScope)

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

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
            it.copy(uniqueId = uniqueId, uniqueIdError = null, loginError = null)
        }
    }

    private fun handlePasswordChanged(password: String) {
        _uiState.update {
            it.copy(password = password, passwordError = null, loginError = null)
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
                _uiState.update { it.copy(schoolError = schoolError, passwordError = passwordError) }
                if (schoolValidation.isValid && passwordValidation.isValid) performLogin()
            }
            LoginType.MEMBER -> {
                val uniqueIdValidation = LoginValidator.validateUniqueId(currentState.uniqueId)
                val uniqueIdError = uniqueIdValidation.errorMessage?.let { ValidationError.valueOf(it) }
                _uiState.update { it.copy(uniqueIdError = uniqueIdError, passwordError = passwordError) }
                if (uniqueIdValidation.isValid && passwordValidation.isValid) performLogin()
            }
        }
    }

    private fun performLogin() {
        val currentState = _uiState.value
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, loginError = null) }

            val loginAs: LoginAs
            val identifier: String
            when (currentState.loginType) {
                LoginType.MITRA -> {
                    loginAs = LoginAs.MITRA
                    identifier = currentState.selectedSchool?.name ?: ""
                }
                LoginType.MEMBER -> {
                    loginAs = LoginAs.MEMBER
                    identifier = currentState.uniqueId
                }
            }

            when (val result = loginUseCase(loginAs, identifier, currentState.password)) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoginSuccessful = true,
                            password = ""
                        )
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            loginError = result.message
                        )
                    }
                }
                is ApiResult.Loading -> Unit
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
        // Navigation handled by the Screen composable via navigateToForgot callback
    }
}
