package com.subot.profile.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import com.subot.core.domain.result.ApiResult
import com.subot.core.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userPreferences: UserPreferences,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.darkModeEnabledFlow().collectLatest { enabled ->
                _uiState.update { it.copy(darkModeEnabled = enabled) }
            }
        }
        viewModelScope.launch {
            userPreferences.getSelectedLanguage().collectLatest { language ->
                _uiState.update { it.copy(selectedLanguage = language) }
            }
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ToggleDarkMode -> toggleDarkMode(event.enabled)
            is ProfileEvent.SetLanguage -> setLanguage(event.languageCode)
            is ProfileEvent.Logout -> logout()
            is ProfileEvent.ClearLogoutError -> _uiState.update { it.copy(logoutError = null) }
        }
    }

    private fun toggleDarkMode(enabled: Boolean) {
        _uiState.update { it.copy(darkModeEnabled = enabled) }
        viewModelScope.launch {
            userPreferences.setDarkModeEnabled(enabled)
        }
    }

    private fun setLanguage(languageCode: String) {
        _uiState.update { it.copy(selectedLanguage = languageCode) }
        viewModelScope.launch {
            userPreferences.setSelectedLanguage(languageCode)
        }
    }

    private fun logout() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoggingOut = true, logoutError = null) }
            val result = logoutUseCase()
            when (result) {
                is ApiResult.Success -> {
                    _uiState.update { it.copy(isLoggingOut = false, isLogoutSuccessful = true) }
                }
                is ApiResult.Error -> {
                    // LogoutUseCase always clears local state, so navigate out regardless
                    _uiState.update { it.copy(isLoggingOut = false, isLogoutSuccessful = true) }
                }
                is ApiResult.Loading -> Unit
            }
        }
    }
}
