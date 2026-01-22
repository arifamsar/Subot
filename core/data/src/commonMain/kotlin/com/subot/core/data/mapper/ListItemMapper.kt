package com.subot.core.data.mapper

import com.subot.core.data.dto.ListItemDto
import com.subot.core.data.dto.ListResponseDto
import com.subot.core.domain.model.ListItem
import com.subot.core.domain.model.PaginatedResult

fun ListItemDto.toDomain(): ListItem {
    return ListItem(
        id = id,
        title = title,
        description = description,
        createdAt = createdAt
    )
}

fun <T, R> ListResponseDto<T>.toDomain(mapper: (T) -> R): PaginatedResult<R> {
    return PaginatedResult(
        items = data.map(mapper),
        total = total ?: 0,
        page = page ?: 1,
        limit = limit ?: 10
    )
}
