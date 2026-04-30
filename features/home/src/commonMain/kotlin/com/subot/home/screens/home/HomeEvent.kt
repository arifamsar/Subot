package com.subot.home.screens.home

sealed class HomeEvent {
    object LoadDashboard : HomeEvent()
    object Refresh : HomeEvent()
}
