package com.subot.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(private val userPreferences: UserPreferences): ViewModel() {

    private val _darkModeEnabled = MutableStateFlow(false)
    val darkModeEnabled: StateFlow<Boolean> = _darkModeEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.darkModeEnabledFlow().collectLatest { enabled ->
                _darkModeEnabled.value = enabled
            }
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        _darkModeEnabled.value = enabled
        viewModelScope.launch {
            userPreferences.setDarkModeEnabled(enabled)
        }
    }

    fun logout(onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            userPreferences.setLoggedIn(false)
            onComplete()
        }
    }
}
