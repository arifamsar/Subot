package com.subot.core.domain.service

import com.subot.core.data.dto.ListItemDto
import com.subot.core.data.dto.ListResponseDto

interface ApiService {
    suspend fun getListItems(page: Int = 1, limit: Int = 10): ListResponseDto<ListItemDto>
    suspend fun getItemById(id: Int): ListItemDto
}