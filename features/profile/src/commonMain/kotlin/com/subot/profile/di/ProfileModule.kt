package com.subot.profile.di

import com.subot.profile.screens.change_password.ChangePasswordViewModel
import com.subot.profile.screens.edit_profile.EditProfileViewModel
import com.subot.profile.screens.members.MembersViewModel
import com.subot.profile.screens.penanggung_jawab.PenanggungJawabViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule = module {
    viewModelOf(::MembersViewModel)
    viewModelOf(::PenanggungJawabViewModel)
    viewModelOf(::EditProfileViewModel)
    viewModelOf(::ChangePasswordViewModel)
}

