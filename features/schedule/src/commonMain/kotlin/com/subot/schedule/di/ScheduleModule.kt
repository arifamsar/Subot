package com.subot.schedule.di

import com.subot.schedule.ScheduleViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val scheduleModule = module {
    viewModelOf(::ScheduleViewModel)
}
