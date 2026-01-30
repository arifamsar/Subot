package com.sukarobot.subot.ui.di

import com.subot.profile.screens.profile.ProfileViewModel
import com.sukarobot.subot.ui.screens.forgot_password.ForgotPasswordViewModel
import com.sukarobot.subot.ui.screens.login.LoginViewModel
import com.sukarobot.subot.ui.screens.onboarding.OnboardingViewModel
import com.sukarobot.subot.ui.screens.splash.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}