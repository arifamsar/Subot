package com.subot.core.data.di

import com.subot.core.data.database.AppDatabase
import com.subot.core.data.database.getDatabaseBuilder
import com.subot.core.data.database.getRoomDatabase
import com.subot.core.data.notification.createAlarmeePlatformConfiguration
import com.subot.core.data.repository.NotificationRepositoryImpl
import com.subot.core.domain.repository.NotificationRepository
import com.tweener.alarmee.AlarmeeService
import com.tweener.alarmee.createAlarmeeService
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        getRoomDatabase(getDatabaseBuilder())
    }
    single<NotificationRepository> {
        NotificationRepositoryImpl(get())
    }
    single<AlarmeeService> {
        createAlarmeeService().apply {
            initialize(platformConfiguration = createAlarmeePlatformConfiguration())
        }
    }
}
