package com.subot.core.domain.model

/**
 * Represents the full user profile returned by /auth/me and /profile/me.
 */
data class UserProfile(
    val type: String,
    val profile: UserProfileSummary
)

