package com.subot.core.data.notification

import android.app.NotificationManager
import com.tweener.alarmee.configuration.AlarmeeAndroidPlatformConfiguration
import com.tweener.alarmee.channel.AlarmeeNotificationChannel
import com.tweener.alarmee.configuration.AlarmeePlatformConfiguration

actual fun createAlarmeePlatformConfiguration(): AlarmeePlatformConfiguration =
    AlarmeeAndroidPlatformConfiguration(
        notificationIconResId = android.R.drawable.ic_dialog_info,
        notificationIconColor = androidx.compose.ui.graphics.Color.Transparent,
        useExactScheduling = false,
        notificationChannels = listOf(
            AlarmeeNotificationChannel(
                id = "download_channel",
                name = "Downloads",
                importance = NotificationManager.IMPORTANCE_DEFAULT
            )
        )
    )
