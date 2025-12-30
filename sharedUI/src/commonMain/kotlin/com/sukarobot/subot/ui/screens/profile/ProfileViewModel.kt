package com.sukarobot.subot.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import kotlinx.coroutines.launch

class ProfileViewModel(private val userPreferences: UserPreferences): ViewModel() {

    fun logout(onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            userPreferences.setLoggedIn(false)
            onComplete()
        }
    }
}