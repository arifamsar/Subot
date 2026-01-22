package com.subot.core.domain.model

data class PaginatedResult<T>(
    val items: List<T>,
    val total: Int,
    val page: Int,
    val limit: Int
)
