package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListItemDto(
    @SerialName("id")
    val id: Int,
    
    @SerialName("title")
    val title: String,
    
    @SerialName("description")
    val description: String? = null,
    
    @SerialName("createdAt")
    val createdAt: String? = null
)