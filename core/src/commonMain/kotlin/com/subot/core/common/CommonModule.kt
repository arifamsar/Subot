package com.subot.core.common

import org.koin.dsl.module

val commonModule = module {
    single<AppDispatchers> { AppDispatchers }
}