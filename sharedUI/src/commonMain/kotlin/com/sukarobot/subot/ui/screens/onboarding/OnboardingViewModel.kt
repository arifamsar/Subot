package com.sukarobot.subot.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OnboardingViewModel(private val userPreferences: UserPreferences): ViewModel() {

    private val _isOnboardingCompleted = MutableStateFlow(false)
    val isOnboardingCompleted = _isOnboardingCompleted

    private val _darkModeEnabled = MutableStateFlow(false)
    val darkModeEnabled: StateFlow<Boolean> = _darkModeEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.darkModeEnabledFlow().collectLatest { enabled ->
                _darkModeEnabled.value = enabled
            }
        }
    }

    suspend fun completeOnboarding() {
        userPreferences.setOnboardingCompleted(true)
        _isOnboardingCompleted.emit(true)
    }

    fun toggleDarkMode(enabled: Boolean) {
        _darkModeEnabled.value = enabled
        viewModelScope.launch {
            userPreferences.setDarkModeEnabled(enabled)
        }
    }

}