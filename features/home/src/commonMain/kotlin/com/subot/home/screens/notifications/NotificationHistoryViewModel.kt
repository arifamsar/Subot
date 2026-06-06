package com.subot.home.screens.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationHistoryViewModel(
    private val repository: NotificationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationHistoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(NotificationHistoryEvent.LoadNotifications)
    }

    fun onEvent(event: NotificationHistoryEvent) {
        when (event) {
            is NotificationHistoryEvent.LoadNotifications -> loadNotifications()
            is NotificationHistoryEvent.MarkAsRead -> markAsRead(event.id)
            is NotificationHistoryEvent.ClearAll -> clearAll()
        }
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getNotifications().collectLatest { list ->
                _uiState.update {
                    it.copy(
                        notifications = list,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    private fun markAsRead(id: Long) {
        viewModelScope.launch {
            repository.markAsRead(id)
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            repository.clearAllNotifications()
        }
    }
}
