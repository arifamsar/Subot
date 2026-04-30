package com.subot.core.data.repository

import com.subot.core.data.dto.InvoiceDto
import com.subot.core.data.dto.PaginatedFinanceDto
import com.subot.core.data.dto.SnapTokenDto
import com.subot.core.data.dto.TransactionHistoryDto
import com.subot.core.data.mapper.toDomain
import com.subot.core.data.service.ApiService
import com.subot.core.data.service.UserPreferences
import com.subot.core.data.util.safeApiCall
import com.subot.core.domain.model.Invoice
import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.model.SnapToken
import com.subot.core.domain.model.TransactionHistory
import com.subot.core.domain.repository.FinanceRepository
import com.subot.core.domain.result.ApiResult

class FinanceRepositoryImpl(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : FinanceRepository {
    override suspend fun getInvoices(): ApiResult<List<Invoice>> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<List<InvoiceDto>> {
            apiService.getInvoices(token = token)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.map { it.toDomain() })
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override suspend fun getPaymentHistory(page: Int, perPage: Int): ApiResult<PaginatedData<TransactionHistory>> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<PaginatedFinanceDto<TransactionHistoryDto>> {
            apiService.getPaymentHistory(token = token, page = page, perPage = perPage)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(
                PaginatedData(
                    items = result.data.items.map { it.toDomain() },
                    pagination = result.data.pagination.toDomain()
                )
            )
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }

    override suspend fun requestSnapToken(tagihanId: Int): ApiResult<SnapToken> {
        val token = userPreferences.getAccessToken()
            ?: return ApiResult.Error("Not authenticated", 401)
        val result = safeApiCall<SnapTokenDto> {
            apiService.requestSnapToken(token = token, tagihanId = tagihanId)
        }
        return when (result) {
            is ApiResult.Success -> ApiResult.Success(result.data.toDomain())
            is ApiResult.Error -> result
            is ApiResult.Loading -> result
        }
    }
}
