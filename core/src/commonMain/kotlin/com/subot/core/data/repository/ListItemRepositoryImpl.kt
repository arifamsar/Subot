package com.subot.core.data.repository

import com.subot.core.data.dto.ListItemDto
import com.subot.core.data.dto.ListResponseDto
import com.subot.core.domain.repository.ListItemRepository
import com.subot.core.domain.service.ApiService

class ListItemRepositoryImpl(
    private val apiService: ApiService
) : ListItemRepository {
    
    override suspend fun getListItems(page: Int, limit: Int): ListResponseDto<ListItemDto> {
        return apiService.getListItems(page, limit)
    }
    
    override suspend fun getItemById(id: Int): ListItemDto {
        return apiService.getItemById(id)
    }
}