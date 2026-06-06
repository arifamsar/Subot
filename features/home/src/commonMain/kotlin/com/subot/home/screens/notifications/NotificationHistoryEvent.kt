package com.subot.home.screens.notifications

sealed interface NotificationHistoryEvent {
    data object LoadNotifications : NotificationHistoryEvent
    data class MarkAsRead(val id: Long) : NotificationHistoryEvent
    data object ClearAll : NotificationHistoryEvent
}
