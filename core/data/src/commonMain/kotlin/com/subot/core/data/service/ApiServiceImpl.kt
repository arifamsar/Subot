package com.subot.core.data.service

import com.subot.core.data.dto.ListItemDto
import com.subot.core.data.dto.ListResponseDto
import com.subot.core.data.dto.LoginRequestDto
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiServiceImpl(
    private val httpClient: HttpClient
) : ApiService {

    // ---------- Legacy ----------

    override suspend fun getListItems(page: Int, limit: Int): ListResponseDto<ListItemDto> {
        return httpClient.get("items") {
            parameter("page", page)
            parameter("limit", limit)
        }.body()
    }

    override suspend fun getItemById(id: Int): ListItemDto {
        return httpClient.get("items/$id").body()
    }

    // ---------- Public ----------

    override suspend fun getSchools(page: Int, perPage: Int): HttpResponse {
        return httpClient.get("public/schools") {
            parameter("page", page)
            parameter("per_page", perPage)
        }
    }

    // ---------- Auth ----------

    override suspend fun login(request: LoginRequestDto): HttpResponse {
        return httpClient.post("auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    override suspend fun getMe(token: String): HttpResponse {
        return httpClient.get("auth/me") {
            bearerAuth(token)
        }
    }

    override suspend fun logout(token: String): HttpResponse {
        return httpClient.post("auth/logout") {
            bearerAuth(token)
        }
    }

    // ---------- Profile ----------

    override suspend fun getProfile(token: String): HttpResponse {
        return httpClient.get("profile/me") {
            bearerAuth(token)
        }
    }
}