package com.sukarobot.subot.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(private val userPreferences: UserPreferences): ViewModel() {

    private val _isOnboardingCompleted = MutableStateFlow(false)
    val isOnboardingCompleted = _isOnboardingCompleted

    val darkModeEnabled: Flow<Boolean> = userPreferences.darkModeEnabledFlow()

    suspend fun completeOnboarding() {
        userPreferences.setOnboardingCompleted(true)
        _isOnboardingCompleted.emit(true)
    }

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            userPreferences.setDarkModeEnabled(enabled)
        }
    }

}