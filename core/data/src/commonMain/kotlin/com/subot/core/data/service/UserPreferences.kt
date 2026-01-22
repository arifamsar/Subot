package com.subot.core.data.service

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreferences(private val dataStore: DataStore<Preferences>) {

    suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[onboarding_completed] = completed
        }
    }

    suspend fun isOnboardingCompleted(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[onboarding_completed] ?: false
    }

    suspend fun setLoggedIn(loggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[is_logged_in] = loggedIn
        }
    }

    suspend fun isLoggedIn(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[is_logged_in] ?: false
    }

    suspend fun setDarkModeEnabled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[is_dark_mode] = enabled
        }
    }

    suspend fun isDarkModeEnabled(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[is_dark_mode] ?: false
    }

    fun darkModeEnabledFlow(): Flow<Boolean> =
        dataStore.data.map { preferences -> preferences[is_dark_mode] ?: false }

    companion object {
        val onboarding_completed = booleanPreferencesKey("onboarding_completed")
        val is_logged_in = booleanPreferencesKey("is_logged_in")
        val is_dark_mode = booleanPreferencesKey("is_dark_mode")
    }
}