package com.subot.core.data.di

import com.subot.core.data.service.UserPreferences
import com.subot.core.data.service.createPreferencesDataStore
import org.koin.dsl.module

val preferencesModule = module {
    single { UserPreferences(createPreferencesDataStore()) }
}