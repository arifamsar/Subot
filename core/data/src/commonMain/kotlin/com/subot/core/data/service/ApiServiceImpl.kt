package com.subot.core.data.service

import com.subot.core.data.dto.ListItemDto
import com.subot.core.data.dto.ListResponseDto
import com.subot.core.data.dto.LoginRequestDto
import com.subot.core.data.dto.PenanggungJawabRequestDto
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
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

    override suspend fun updateMemberProfile(
        token: String,
        namaLengkap: String,
        tempatLahir: String?,
        tanggalLahir: String?,
        kelas: String?,
        alamat: String?,
        telephone: String?,
        namaOrtu: String?,
        workOrtu: String?,
        fotoProfile: ByteArray?,
        fotoProfileName: String?
    ): HttpResponse {
        return httpClient.post("profile/member") {
            bearerAuth(token)
            setBody(MultiPartFormDataContent(
                formData {
                    append("nama_lengkap", namaLengkap)
                    tempatLahir?.let { append("tempat_lahir", it) }
                    tanggalLahir?.let { append("tanggal_lahir", it) }
                    kelas?.let { append("kelas", it) }
                    alamat?.let { append("alamat", it) }
                    telephone?.let { append("telephone", it) }
                    namaOrtu?.let { append("nama_ortu", it) }
                    workOrtu?.let { append("work_ortu", it) }
                    if (fotoProfile != null) {
                        append("foto_profile", fotoProfile, Headers.build {
                            append(HttpHeaders.ContentType, getContentTypeForFileName(fotoProfileName ?: "profile.jpg"))
                            append(HttpHeaders.ContentDisposition, "filename=\"${fotoProfileName ?: "profile.jpg"}\"")
                        })
                    }
                }
            ))
        }
    }

    private fun getContentTypeForFileName(fileName: String): String {
        return when (fileName.substringAfterLast('.').lowercase()) {
            "png" -> "image/png"
            "gif" -> "image/gif"
            "webp" -> "image/webp"
            else -> "image/jpeg"
        }
    }

    override suspend fun getSupervise(token: String): HttpResponse {
        return httpClient.get("supervise") {
            bearerAuth(token)
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

    override suspend fun exportScheduleReport(
        token: String,
        scheduleIds: List<Int>?,
        startDate: String?,
        endDate: String?
    ): HttpResponse {
        return httpClient.post("schedules/export") {
            bearerAuth(token)
            header(io.ktor.http.HttpHeaders.Accept, "application/pdf, application/json")
            contentType(ContentType.Application.Json)
            setBody(com.subot.core.data.dto.ExportRequestDto(scheduleIds, startDate, endDate))
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