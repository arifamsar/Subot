package com.subot.home.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.domain.usecase.GetDashboardUseCase
import com.subot.core.domain.result.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDashboardUseCase: GetDashboardUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(HomeEvent.LoadDashboard)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadDashboard -> loadDashboard()
            is HomeEvent.Refresh -> loadDashboard()
        }
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = getDashboardUseCase()) {
                is ApiResult.Success -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            dashboard = result.data,
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
