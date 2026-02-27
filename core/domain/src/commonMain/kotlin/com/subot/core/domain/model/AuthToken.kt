package com.subot.core.domain.model

/**
 * Represents the authenticated user's token and type after a successful login.
 */
data class AuthToken(
    val tokenType: String,
    val accessToken: String,
    val userType: String,
    val user: AuthUser
)

data class AuthUser(
    val type: String,
    val profile: UserProfileSummary
)

data class UserProfileSummary(
    val id: Int,
    val nis: String?
)

