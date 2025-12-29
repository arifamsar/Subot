package com.sukarobot.subot.ui.screens.login

/**
 * Validation result for form fields
 */
data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)

/**
 * Validator utility for login form
 */
object LoginValidator {
    
    fun validateEmail(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult(
                isValid = false,
                errorMessage = "Email is required"
            )
            !isValidEmailFormat(email) -> ValidationResult(
                isValid = false,
                errorMessage = "Please enter a valid email address"
            )
            else -> ValidationResult(isValid = true)
        }
    }
    
    fun validatePassword(password: String): ValidationResult {
        return when {
            password.isBlank() -> ValidationResult(
                isValid = false,
                errorMessage = "Password is required"
            )
            password.length < 8 -> ValidationResult(
                isValid = false,
                errorMessage = "Password must be at least 8 characters"
            )
            !password.any { it.isUpperCase() } -> ValidationResult(
                isValid = false,
                errorMessage = "Password must contain at least one uppercase letter"
            )
            !password.any { it.isDigit() } -> ValidationResult(
                isValid = false,
                errorMessage = "Password must contain at least one number"
            )
            else -> ValidationResult(isValid = true)
        }
    }
    
    private fun isValidEmailFormat(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return email.matches(emailRegex)
    }
}
