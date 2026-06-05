package com.subot.core.data.util

import co.touchlab.kermit.Logger
import com.subot.core.data.dto.ApiResponseDto
import com.subot.core.domain.result.ApiResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import Subot.core.data.BuildConfig

/**
 * Safely executes a network call and wraps the result in [ApiResult].
 * Handles HTTP error codes and unexpected exceptions.
 */
suspend inline fun <reified T> safeApiCall(crossinline call: suspend () -> HttpResponse): ApiResult<T> {
    return try {
        val response = call()
        if (response.status.isSuccess()) {
            val body = response.body<ApiResponseDto<T>>()
            val data = body.data
            if (data != null) {
                ApiResult.Success(data)
            } else {
                ApiResult.Error(body.message ?: "No data returned", response.status.value)
            }
        } else {
            val errorBody = try {
                response.body<ApiResponseDto<T>>()
            } catch (_: Exception) {
                null
            }
            val message = errorBody?.message ?: response.status.description
            logApiError(response.status.value, message)
            ApiResult.Error(message, response.status.value)
        }
    } catch (e: Exception) {
        logNetworkException(e)
        ApiResult.Error(e.message ?: "Unknown network error")
    }
}

@PublishedApi
internal fun logApiError(code: Int, message: String) {
    Logger.withTag("NetworkUtil").e { "API Error $code: $message" }
}

@PublishedApi
internal fun logNetworkException(e: Exception) {
    Logger.withTag("NetworkUtil").e(e) { "Network call failed" }
}

/**
 * Formats image URLs by prepending BuildConfig.URL if the path is relative.
 */
fun formatImageUrl(path: String?): String? {
    if (path.isNullOrBlank()) return null
    if (path.startsWith("http://") || path.startsWith("https://")) {
        return path
    }
    val baseUrl = BuildConfig.URL.removeSuffix("/")
    var cleanPath = if (path.startsWith("/")) path else "/$path"
    if (cleanPath.startsWith("/storage")) {
        cleanPath = cleanPath.removePrefix("/storage")
    }
    val finalPath = if (cleanPath.startsWith("/")) cleanPath else "/$cleanPath"
    return "$baseUrl$finalPath"
}



