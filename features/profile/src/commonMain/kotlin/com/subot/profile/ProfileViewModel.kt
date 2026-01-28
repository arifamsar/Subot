package com.subot.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import com.subot.core.domain.AppLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(private val userPreferences: UserPreferences): ViewModel() {

    private val _darkModeEnabled = MutableStateFlow(false)
    val darkModeEnabled: StateFlow<Boolean> = _darkModeEnabled.asStateFlow()

    private val _selectedLanguage = MutableStateFlow(AppLanguage.INDONESIAN.code)
    val selectedLanguage: StateFlow<String> = _selectedLanguage.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.darkModeEnabledFlow().collectLatest { enabled ->
                _darkModeEnabled.value = enabled
            }
        }
        viewModelScope.launch {
            userPreferences.getSelectedLanguage().collectLatest { language ->
                _selectedLanguage.value = language
            }
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        _darkModeEnabled.value = enabled
        viewModelScope.launch {
            userPreferences.setDarkModeEnabled(enabled)
        }
    }

    fun setLanguage(languageCode: String) {
        _selectedLanguage.value = languageCode
        viewModelScope.launch {
            userPreferences.setSelectedLanguage(languageCode)
        }
    }

    fun logout(onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            userPreferences.setLoggedIn(false)
            onComplete()
        }
    }
}
