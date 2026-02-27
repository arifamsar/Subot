package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolDto(
    @SerialName("id")
    val id: Int,

    @SerialName("sekolah")
    val sekolah: String
)

@Serializable
data class PaginationDto(
    @SerialName("current_page")
    val currentPage: Int,

    @SerialName("last_page")
    val lastPage: Int,

    @SerialName("per_page")
    val perPage: Int,

    @SerialName("total")
    val total: Int,

    @SerialName("from")
    val from: Int,

    @SerialName("to")
    val to: Int,

    @SerialName("has_more_pages")
    val hasMorePages: Boolean,

    @SerialName("next_page_url")
    val nextPageUrl: String? = null,

    @SerialName("prev_page_url")
    val prevPageUrl: String? = null
)

@Serializable
data class PaginatedSchoolsDto(
    @SerialName("items")
    val items: List<SchoolDto>,

    @SerialName("pagination")
    val pagination: PaginationDto
)

