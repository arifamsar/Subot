package com.sukarobot.subot.ui.screens.login

import com.subot.core.domain.model.School

/**
 * UI state for the login screen following MVI pattern.
 * Supports two login modes: Mitra (school + password) and Member (unique ID + password).
 *
 * Note: [schoolsPagingData] is intentionally kept outside this data class (in the ViewModel)
 * because [kotlinx.coroutines.flow.Flow] does not support structural equality, which would
 * break copy() semantics and Compose snapshot comparisons.
 */
data class LoginUiState(
    // Login type
    val loginType: LoginType = LoginType.MITRA,

    // Mitra-specific fields
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
    val isFormValid: Boolean
        get() = when (loginType) {
            LoginType.MITRA -> selectedSchool != null && schoolError == null &&
                    password.isNotEmpty() && passwordError == null
            LoginType.MEMBER -> uniqueId.isNotEmpty() && uniqueIdError == null &&
                    password.isNotEmpty() && passwordError == null
        }
}