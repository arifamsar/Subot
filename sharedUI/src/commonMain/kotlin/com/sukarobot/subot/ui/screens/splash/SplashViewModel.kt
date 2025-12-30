package com.sukarobot.subot.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(private val userPreferences: UserPreferences): ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState

    init {
        loadStatuses()
    }

    private fun loadStatuses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val isLoggedIn = userPreferences.isLoggedIn()
                val isOnboardingCompleted = userPreferences.isOnboardingCompleted()

                _uiState.update {
                    it.copy(
                        isLoggedIn = isLoggedIn,
                        isOnboardingCompleted = isOnboardingCompleted,
                        isLoading = false,
                        isReady = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }
}