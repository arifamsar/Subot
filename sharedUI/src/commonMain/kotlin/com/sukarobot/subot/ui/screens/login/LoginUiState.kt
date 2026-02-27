package com.sukarobot.subot.ui.screens.login

/**
 * UI state for the login screen following MVI pattern.
 * Supports two login modes: Mitra (school + password) and Member (unique ID + password).
 */
data class LoginUiState(
    // Login type
    val loginType: LoginType = LoginType.MITRA,

    // Mitra-specific fields
    val schools: List<School> = emptyList(),
    val selectedSchool: School? = null,
    val schoolError: ValidationError? = null,
    val isSchoolDropdownExpanded: Boolean = false,

    // Member-specific fields
    val uniqueId: String = "",
    val uniqueIdError: ValidationError? = null,

    // Shared fields
    val password: String = "",
    val passwordError: ValidationError? = null,
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val loginError: String? = null
) {
    /**
     * Check if the login form is valid based on the current login type
     */
    val isFormValid: Boolean
        get() = when (loginType) {
            LoginType.MITRA -> selectedSchool != null && schoolError == null &&
                    password.isNotEmpty() && passwordError == null
            LoginType.MEMBER -> uniqueId.isNotEmpty() && uniqueIdError == null &&
                    password.isNotEmpty() && passwordError == null
        }
}