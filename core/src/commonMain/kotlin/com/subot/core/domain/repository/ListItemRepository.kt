package com.subot.core.domain.repository

import com.subot.core.data.dto.ListItemDto
import com.subot.core.data.dto.ListResponseDto

interface ListItemRepository {
    suspend fun getListItems(page: Int = 1, limit: Int = 10): ListResponseDto<ListItemDto>
    suspend fun getItemById(id: Int): ListItemDto
}