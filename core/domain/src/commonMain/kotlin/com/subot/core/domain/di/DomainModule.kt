package com.subot.core.domain.di

import com.subot.core.domain.usecase.GetAuthMeUseCase
import com.subot.core.domain.usecase.GetSchoolsPagedUseCase
import com.subot.core.domain.usecase.GetSchoolsUseCase
import com.subot.core.domain.usecase.LoginUseCase
import com.subot.core.domain.usecase.LogoutUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { LoginUseCase(get()) }
    factory { LogoutUseCase(get()) }
    factory { GetAuthMeUseCase(get()) }
    factory { GetSchoolsUseCase(get()) }
    factory { GetSchoolsPagedUseCase(get()) }
}

