package com.subot.core.domain.model

data class NotificationHistory(
    val id: Long,
    val title: String,
    val body: String,
    val timestamp: Long,
    val isRead: Boolean
)
