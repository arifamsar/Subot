package com.subot.core.domain.repository

import com.subot.core.domain.model.AuthToken
import com.subot.core.domain.model.UserProfile
import com.subot.core.domain.result.ApiResult

/**
 * Supported login roles as defined by the API.
 */
enum class LoginAs(val value: String) {
    MEMBER("member"),
    MITRA("mitra")
}

interface AuthRepository {
    /**
     * Login with identifier and password.
     * - [loginAs] = MEMBER → identifier is the member NIS
     * - [loginAs] = MITRA  → identifier is the sekolah name
     */
    suspend fun login(
        loginAs: LoginAs,
        identifier: String,
        password: String,
        deviceName: String
    ): ApiResult<AuthToken>

    /**
     * Returns the currently authenticated user's profile.
     * Requires a valid access token stored in preferences.
     */
    suspend fun getMe(): ApiResult<UserProfile>

    /**
     * Revokes the current access token (logout).
     */
    suspend fun logout(): ApiResult<Unit>

    /**
     * Returns the locally stored access token, or null if not logged in.
     */
    suspend fun getAccessToken(): String?

    /**
     * Persists the access token to secure storage.
     */
    suspend fun saveAccessToken(token: String)

    /**
     * Clears the locally stored access token.
     */
    suspend fun clearAccessToken()

    /**
     * Persists the logged-in flag so the splash screen can restore session on restart.
     */
    suspend fun setLoggedIn(loggedIn: Boolean)
}

