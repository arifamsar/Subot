package com.sukarobot.subot.ui.screens.login

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
    EMAIL_REQUIRED,
    INVALID_EMAIL_FORMAT,
    PASSWORD_REQUIRED,
    PASSWORD_TOO_SHORT,
    PASSWORD_NO_UPPERCASE,
    PASSWORD_NO_NUMBER
}

/**
 * Validator utility for login form
 */
object LoginValidator {

    fun validateEmail(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult(
                isValid = false,
                errorMessage = ValidationError.EMAIL_REQUIRED.name
            )
            !isValidEmailFormat(email) -> ValidationResult(
                isValid = false,
                errorMessage = ValidationError.INVALID_EMAIL_FORMAT.name
            )
            else -> ValidationResult(isValid = true)
        }
    }

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
            !password.any { it.isUpperCase() } -> ValidationResult(
                isValid = false,
                errorMessage = ValidationError.PASSWORD_NO_UPPERCASE.name
            )
            !password.any { it.isDigit() } -> ValidationResult(
                isValid = false,
                errorMessage = ValidationError.PASSWORD_NO_NUMBER.name
            )
            else -> ValidationResult(isValid = true)
        }
    }

    private fun isValidEmailFormat(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return email.matches(emailRegex)
    }
}
