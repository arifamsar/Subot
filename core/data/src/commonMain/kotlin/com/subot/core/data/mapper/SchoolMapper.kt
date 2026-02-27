package com.subot.core.data.mapper

import com.subot.core.data.dto.PaginatedSchoolsDto
import com.subot.core.data.dto.PaginationDto
import com.subot.core.data.dto.SchoolDto
import com.subot.core.domain.model.Pagination
import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.model.School

fun SchoolDto.toDomain(): School = School(
    id = id,
    name = sekolah
)

fun PaginationDto.toDomain(): Pagination = Pagination(
    currentPage = currentPage,
    lastPage = lastPage,
    perPage = perPage,
    total = total,
    from = from,
    to = to,
    hasMorePages = hasMorePages,
    nextPageUrl = nextPageUrl,
    prevPageUrl = prevPageUrl
)

fun PaginatedSchoolsDto.toDomain(): PaginatedData<School> = PaginatedData(
    items = items.map { it.toDomain() },
    pagination = pagination.toDomain()
)

