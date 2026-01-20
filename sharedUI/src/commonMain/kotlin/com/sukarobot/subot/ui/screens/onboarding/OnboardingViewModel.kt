package com.sukarobot.subot.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    init {
        observeDarkMode()
    }

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.CompleteOnboarding -> completeOnboarding()
            is OnboardingEvent.ToggleDarkMode -> toggleDarkMode(event.enabled)
        }
    }

    private fun observeDarkMode() {
        viewModelScope.launch {
            userPreferences.darkModeEnabledFlow().collect { isEnabled ->
                _state.update { it.copy(isDarkMode = isEnabled) }
            }
        }
    }

    private fun completeOnboarding() {
        viewModelScope.launch {
            userPreferences.setOnboardingCompleted(true)
            _state.update { it.copy(isOnboardingCompleted = true) }
        }
    }

    private fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            userPreferences.setDarkModeEnabled(enabled)
        }
    }
}
