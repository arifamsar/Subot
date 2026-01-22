package com.subot.core.data.di

import org.koin.dsl.module

val dataModule = module {
    includes(
        preferencesModule,
        httpModule
    )
}