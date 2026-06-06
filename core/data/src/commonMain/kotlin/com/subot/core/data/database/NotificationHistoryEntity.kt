package com.subot.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.subot.core.domain.model.NotificationHistory

@Entity(tableName = "notification_history")
data class NotificationHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val body: String,
    val timestamp: Long,
    val isRead: Boolean = false
) {
    fun toDomain(): NotificationHistory = NotificationHistory(
        id = id,
        title = title,
        body = body,
        timestamp = timestamp,
        isRead = isRead
    )
}
