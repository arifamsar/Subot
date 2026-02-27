package com.subot.core.data.di

import com.subot.core.domain.di.domainModule
import org.koin.dsl.module

val dataModule = module {
    includes(
        preferencesModule,
        httpModule,
        domainModule
    )
}