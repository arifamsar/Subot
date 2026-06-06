package com.subot.profile.screens.members

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.domain.result.ApiResult
import com.subot.core.domain.usecase.GetProfileMembersUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MembersViewModel(
    private val getProfileMembersUseCase: GetProfileMembersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MembersUiState())
    val uiState: StateFlow<MembersUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadMembers()
    }

    fun onEvent(event: MembersEvent) {
        when (event) {
            is MembersEvent.SearchMembers -> searchMembers(event.query)
            is MembersEvent.RefreshMembers -> loadMembers()
            is MembersEvent.LoadMore -> loadMore()
            is MembersEvent.ClearError -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun loadMembers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, currentPage = 1) }
            try {
                val result = getProfileMembersUseCase(
                    page = 1,
                    perPage = 20,
                    search = null
                )
                when (result) {
                    is ApiResult.Success -> {
                        val data = result.data
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                members = data.items,
                                hasMore = data.pagination.hasMorePages,
                                currentPage = 1,
                                error = null
                            )
                        }
                    }
                    is ApiResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message,
                                members = emptyList()
                            )
                        }
                    }
                    is ApiResult.Loading -> Unit
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Terjadi kesalahan: ${e.message}"
                    )
                }
            }
        }
    }

    private fun loadMore() {
        val state = _uiState.value
        if (state.isLoading || !state.hasMore) return

        viewModelScope.launch {
            _uiState.update { it.copy(refreshing = true) }
            try {
                val nextPage = state.currentPage + 1
                val result = getProfileMembersUseCase(
                    page = nextPage,
                    perPage = 20,
                    search = state.searchQuery
                )
                when (result) {
                    is ApiResult.Success -> {
                        val data = result.data
                        _uiState.update {
                            it.copy(
                                refreshing = false,
                                members = it.members + data.items,
                                hasMore = data.pagination.hasMorePages,
                                currentPage = nextPage
                            )
                        }
                    }
                    is ApiResult.Error -> {
                        _uiState.update {
                            it.copy(
                                refreshing = false,
                                error = result.message
                            )
                        }
                    }
                    is ApiResult.Loading -> Unit
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        refreshing = false,
                        error = "Terjadi kesalahan: ${e.message}"
                    )
                }
            }
        }
    }

    private fun searchMembers(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            _uiState.update { it.copy(isLoading = true, error = null, currentPage = 1) }
            try {
                val result = getProfileMembersUseCase(
                    page = 1,
                    perPage = 20,
                    search = query.takeIf { it.isNotBlank() }
                )
                when (result) {
                    is ApiResult.Success -> {
                        val data = result.data
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                members = data.items,
                                hasMore = data.pagination.hasMorePages,
                                currentPage = 1,
                                error = null
                            )
                        }
                    }
                    is ApiResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message,
                                members = emptyList()
                            )
                        }
                    }
                    is ApiResult.Loading -> Unit
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Terjadi kesalahan: ${e.message}"
                    )
                }
            }
        }
    }
}
