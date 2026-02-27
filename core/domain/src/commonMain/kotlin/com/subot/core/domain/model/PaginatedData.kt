package com.subot.core.domain.model

data class Pagination(
    val currentPage: Int,
    val lastPage: Int,
    val perPage: Int,
    val total: Int,
    val from: Int,
    val to: Int,
    val hasMorePages: Boolean,
    val nextPageUrl: String?,
    val prevPageUrl: String?
)

data class PaginatedData<T>(
    val items: List<T>,
    val pagination: Pagination
)

