package com.subot.core.data.service

import com.subot.core.data.dto.ListItemDto
import com.subot.core.data.dto.ListResponseDto
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*

class ApiServiceImpl(
    private val httpClient: HttpClient
) : ApiService {
    
    override suspend fun getListItems(page: Int, limit: Int): ListResponseDto<ListItemDto> {
        return httpClient.get("/api/items") {
            parameter("page", page)
            parameter("limit", limit)
        }.body()
    }
    
    override suspend fun getItemById(id: Int): ListItemDto {
        return httpClient.get("/api/items/$id").body()
    }
}