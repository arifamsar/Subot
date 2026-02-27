package com.subot.core.data.service

import com.subot.core.data.dto.ListItemDto
import com.subot.core.data.dto.ListResponseDto
import com.subot.core.data.dto.LoginRequestDto
import io.ktor.client.statement.HttpResponse

interface ApiService {
    // --- Legacy placeholder endpoints ---
    suspend fun getListItems(page: Int = 1, limit: Int = 10): ListResponseDto<ListItemDto>
    suspend fun getItemById(id: Int): ListItemDto

    // --- Public ---
    suspend fun getSchools(page: Int = 1, perPage: Int = 10): HttpResponse

    // --- Auth ---
    suspend fun login(request: LoginRequestDto): HttpResponse
    suspend fun getMe(token: String): HttpResponse
    suspend fun logout(token: String): HttpResponse

    // --- Profile ---
    suspend fun getProfile(token: String): HttpResponse
}