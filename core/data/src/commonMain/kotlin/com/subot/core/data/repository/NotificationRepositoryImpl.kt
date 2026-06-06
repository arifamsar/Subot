package com.subot.core.data.repository

import com.subot.core.data.database.AppDatabase
import com.subot.core.data.database.NotificationHistoryEntity
import com.subot.core.domain.model.NotificationHistory
import com.subot.core.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.Clock

class NotificationRepositoryImpl(
    private val database: AppDatabase
) : NotificationRepository {
    private val dao = database.notificationHistoryDao()

    override fun getNotifications(): Flow<List<NotificationHistory>> {
        return dao.getAllNotifications().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun insertNotification(title: String, body: String) {
        val entity = NotificationHistoryEntity(
            title = title,
            body = body,
            timestamp = Clock.System.now().toEpochMilliseconds(),
            isRead = false
        )
        dao.insertNotification(entity)
    }

    override suspend fun markAsRead(id: Long) {
        dao.markAsRead(id)
    }

    override suspend fun clearAllNotifications() {
        dao.clearAllNotifications()
    }
}
