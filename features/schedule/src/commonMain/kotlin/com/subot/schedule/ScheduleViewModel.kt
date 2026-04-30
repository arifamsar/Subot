package com.subot.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import com.subot.core.domain.result.ApiResult
import com.subot.core.domain.usecase.GetScheduleDetailUseCase
import com.subot.core.domain.usecase.GetSchedulesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getSchedulesUseCase: GetSchedulesUseCase,
    private val getScheduleDetailUseCase: GetScheduleDetailUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.userRoleFlow().collectLatest { role ->
                _uiState.update { it.copy(userRole = role) }
            }
        }
        onEvent(ScheduleEvent.LoadSchedules)
    }

    fun onEvent(event: ScheduleEvent) {
        when (event) {
            is ScheduleEvent.LoadSchedules -> loadSchedules()
            is ScheduleEvent.LoadScheduleDetail -> loadScheduleDetail(event.id)
            is ScheduleEvent.Refresh -> loadSchedules()
        }
    }

    private fun loadSchedules() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = getSchedulesUseCase()) {
                is ApiResult.Success -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            schedules = result.data,
                            error = null
                        ) 
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            error = result.message
                        ) 
                    }
                }
                is ApiResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun loadScheduleDetail(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = getScheduleDetailUseCase(id)) {
                is ApiResult.Success -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            scheduleDetail = result.data,
                            error = null
                        ) 
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            error = result.message
                        ) 
                    }
                }
                is ApiResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
