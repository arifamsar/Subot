package com.sukarobot.subot.ui.screens.login

import com.subot.core.domain.model.School

/**
 * Validation result for form fields
 */
data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)

/**
 * Error codes for validation errors
 */
enum class ValidationError {
    PASSWORD_REQUIRED,
    PASSWORD_TOO_SHORT,
    SCHOOL_REQUIRED,
    UNIQUE_ID_REQUIRED,
    INVALID_UNIQUE_ID_FORMAT
}

/**
 * Validator utility for login form
 */
object LoginValidator {

    fun validatePassword(password: String): ValidationResult {
        return when {
            password.isBlank() -> ValidationResult(
                isValid = false,
                errorMessage = ValidationError.PASSWORD_REQUIRED.name
            )
            password.length < 8 -> ValidationResult(
                isValid = false,
                errorMessage = ValidationError.PASSWORD_TOO_SHORT.name
            )
            else -> ValidationResult(isValid = true)
        }
    }

    fun validateSchoolSelection(school: School?): ValidationResult {
        return if (school == null) {
            ValidationResult(
                isValid = false,
                errorMessage = ValidationError.SCHOOL_REQUIRED.name
            )
        } else {
            ValidationResult(isValid = true)
        }
    }

    fun validateUniqueId(uniqueId: String): ValidationResult {
        return when {
            uniqueId.isBlank() -> ValidationResult(
                isValid = false,
                errorMessage = ValidationError.UNIQUE_ID_REQUIRED.name
            )
            uniqueId.length < 3 -> ValidationResult(
                isValid = false,
                errorMessage = ValidationError.INVALID_UNIQUE_ID_FORMAT.name
            )
            else -> ValidationResult(isValid = true)
        }
    }
}
