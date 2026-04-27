package com.subot.profile.screens.members

import com.subot.core.domain.model.Member

data class MembersUiState(
    val isLoading: Boolean = false,
    val members: List<Member> = emptyList(),
    val error: String? = null,
    val searchQuery: String = "",
    val refreshing: Boolean = false,
    val hasMore: Boolean = false,
    val currentPage: Int = 1
)
