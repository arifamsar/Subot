package com.subot.home.screens.home

import com.subot.core.domain.model.Dashboard

data class HomeUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val dashboard: Dashboard? = null,
    val error: String? = null
)
