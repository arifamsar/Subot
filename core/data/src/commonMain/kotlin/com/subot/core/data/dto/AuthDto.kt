package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("login_as")
    val loginAs: String,

    @SerialName("identifier")
    val identifier: String,

    @SerialName("password")
    val password: String,

    @SerialName("device_name")
    val deviceName: String
)

@Serializable
data class UserProfileSummaryDto(
    @SerialName("id")
    val id: Int,

    @SerialName("nis")
    val nis: String? = null
)

@Serializable
data class AuthUserDto(
    @SerialName("type")
    val type: String,

    @SerialName("profile")
    val profile: UserProfileSummaryDto
)

@Serializable
data class AuthTokenDto(
    @SerialName("token_type")
    val tokenType: String,

    @SerialName("access_token")
    val accessToken: String,

    @SerialName("user_type")
    val userType: String,

    @SerialName("user")
    val user: AuthUserDto
)

@Serializable
data class UserProfileDto(
    @SerialName("type")
    val type: String,

    @SerialName("profile")
    val profile: UserProfileSummaryDto
)

