package com.subot.core.domain.di

import com.subot.core.domain.usecase.GetAuthMeUseCase
import com.subot.core.domain.usecase.GetDashboardUseCase
import com.subot.core.domain.usecase.GetInvoicesUseCase
import com.subot.core.domain.usecase.GetPaymentHistoryUseCase
import com.subot.core.domain.usecase.GetProfileMembersUseCase
import com.subot.core.domain.usecase.GetProfileUseCase
import com.subot.core.domain.usecase.GetScheduleDetailUseCase
import com.subot.core.domain.usecase.GetSchedulesUseCase
import com.subot.core.domain.usecase.GetSchoolsPagedUseCase
import com.subot.core.domain.usecase.GetSchoolsUseCase
import com.subot.core.domain.usecase.LoginUseCase
import com.subot.core.domain.usecase.LogoutUseCase
import com.subot.core.domain.usecase.RequestSnapTokenUseCase
import com.subot.core.domain.usecase.UpdatePenanggungJawabUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { LoginUseCase(get()) }
    factory { LogoutUseCase(get()) }
    factory { GetAuthMeUseCase(get()) }
    factory { GetSchoolsUseCase(get()) }
    factory { GetSchoolsPagedUseCase(get()) }
    factory { GetProfileUseCase(get()) }
    factory { GetProfileMembersUseCase(get()) }
    factory { UpdatePenanggungJawabUseCase(get()) }
    factory { GetDashboardUseCase(get()) }
    factory { GetSchedulesUseCase(get()) }
    factory { GetScheduleDetailUseCase(get()) }
    factory { GetInvoicesUseCase(get()) }
    factory { GetPaymentHistoryUseCase(get()) }
    factory { RequestSnapTokenUseCase(get()) }
}
