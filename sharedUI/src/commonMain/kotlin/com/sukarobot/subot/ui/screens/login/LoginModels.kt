package com.sukarobot.subot.ui.screens.login

/**
 * Represents the two login methods available in the app
 */
enum class LoginType {
    MITRA,
    MEMBER
}

/**
 * Represents a school that Mitra users can select during login
 */
data class School(
    val id: String,
    val name: String
)

