package com.subot.core.data.service

import com.subot.core.data.dto.ListItemDto
import com.subot.core.data.dto.ListResponseDto
import com.subot.core.data.dto.LoginRequestDto
import com.subot.core.data.dto.PenanggungJawabRequestDto
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

    override suspend fun getProfileMembers(token: String, page: Int, perPage: Int, search: String?): HttpResponse {
        return httpClient.get("profile/members") {
            bearerAuth(token)
            parameter("page", page)
            parameter("per_page", perPage)
            if (search != null && search.isNotEmpty()) {
                parameter("search", search)
            }
        }
    }

    override suspend fun updatePenanggungJawab(token: String, request: PenanggungJawabRequestDto): HttpResponse {
        return httpClient.put("profile/penanggung-jawab") {
            bearerAuth(token)
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    // ---------- Dashboard ----------

    override suspend fun getDashboard(token: String): HttpResponse {
        return httpClient.get("dashboard") {
            bearerAuth(token)
        }
    }

    // ---------- Schedules ----------

    override suspend fun getSchedules(token: String): HttpResponse {
        return httpClient.get("schedules") {
            bearerAuth(token)
        }
    }

    override suspend fun getScheduleDetail(token: String, id: Int): HttpResponse {
        return httpClient.get("schedules/$id") {
            bearerAuth(token)
        }
    }

    // ---------- Finance ----------

    override suspend fun getInvoices(token: String): HttpResponse {
        return httpClient.get("finance/invoices") {
            bearerAuth(token)
        }
    }

    override suspend fun getPaymentHistory(token: String, page: Int, perPage: Int): HttpResponse {
        return httpClient.get("finance/history") {
            bearerAuth(token)
            parameter("page", page)
            parameter("per_page", perPage)
        }
    }

    override suspend fun requestSnapToken(token: String, tagihanId: Int): HttpResponse {
        return httpClient.post("finance/request-token") {
            bearerAuth(token)
            contentType(ContentType.Application.Json)
            setBody(com.subot.core.data.dto.SnapTokenRequestDto(tagihanId))
        }
    }
}