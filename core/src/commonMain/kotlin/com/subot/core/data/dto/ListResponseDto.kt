package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListResponseDto<T>(
    @SerialName("data")
    val data: List<T>,
    
    @SerialName("total")
    val total: Int? = null,
    
    @SerialName("page")
    val page: Int? = null,
    
    @SerialName("limit")
    val limit: Int? = null
)