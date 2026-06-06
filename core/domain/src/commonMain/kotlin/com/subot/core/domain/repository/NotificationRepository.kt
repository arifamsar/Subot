package com.subot.core.domain.repository

import com.subot.core.domain.model.NotificationHistory
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getNotifications(): Flow<List<NotificationHistory>>
    suspend fun insertNotification(title: String, body: String)
    suspend fun markAsRead(id: Long)
    suspend fun clearAllNotifications()
}
