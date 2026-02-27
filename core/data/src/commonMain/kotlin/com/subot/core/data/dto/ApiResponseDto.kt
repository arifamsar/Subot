package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ApiResponseDto<T>(
    @SerialName("status")
    val status: String,

    @SerialName("message")
    val message: String? = null,

    @SerialName("data")
    val data: T? = null,

    @SerialName("errors")
    val errors: JsonElement? = null
)

