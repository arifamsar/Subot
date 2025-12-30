package com.sukarobot.subot.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import com.subot.core.data.service.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow

class OnboardingViewModel(private val userPreferences: UserPreferences): ViewModel() {

    private val _isOnboardingCompleted = MutableStateFlow(false)
    val isOnboardingCompleted = _isOnboardingCompleted

    suspend fun completeOnboarding() {
        userPreferences.setOnboardingCompleted(true)
        _isOnboardingCompleted.emit(true)
    }

}