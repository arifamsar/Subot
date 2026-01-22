package com.subot.core.domain.repository

import com.subot.core.domain.model.ListItem
import com.subot.core.domain.model.PaginatedResult

interface ListItemRepository {
    suspend fun getListItems(page: Int = 1, limit: Int = 10): PaginatedResult<ListItem>
    suspend fun getItemById(id: Int): ListItem
}