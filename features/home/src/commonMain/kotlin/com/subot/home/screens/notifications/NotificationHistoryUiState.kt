package com.subot.home.screens.notifications

import com.subot.core.domain.model.NotificationHistory

data class NotificationHistoryUiState(
    val notifications: List<NotificationHistory> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
