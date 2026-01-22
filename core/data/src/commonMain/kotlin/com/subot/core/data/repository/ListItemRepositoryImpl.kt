package com.subot.core.data.repository

import com.subot.core.data.mapper.toDomain
import com.subot.core.data.service.ApiService
import com.subot.core.domain.model.ListItem
import com.subot.core.domain.model.PaginatedResult
import com.subot.core.domain.repository.ListItemRepository

class ListItemRepositoryImpl(
    private val apiService: ApiService
) : ListItemRepository {
    
    override suspend fun getListItems(page: Int, limit: Int): PaginatedResult<ListItem> {
        val response = apiService.getListItems(page, limit)
        return response.toDomain { it.toDomain() }
    }
    
    override suspend fun getItemById(id: Int): ListItem {
        val dto = apiService.getItemById(id)
        return dto.toDomain()
    }
}
