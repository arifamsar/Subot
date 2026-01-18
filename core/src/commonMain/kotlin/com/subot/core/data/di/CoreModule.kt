package com.subot.core.data.di

import org.koin.dsl.module

val coreModule = module {
    includes(
        preferencesModule,
        httpModule
    )
}