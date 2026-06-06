package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordRequestDto(
    @SerialName("current_password")
    val currentPassword: String,

    @SerialName("password")
    val password: String,

    @SerialName("password_confirmation")
    val passwordConfirmation: String
)
