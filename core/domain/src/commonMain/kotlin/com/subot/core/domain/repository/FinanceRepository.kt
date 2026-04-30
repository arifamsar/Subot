package com.subot.core.domain.repository

import com.subot.core.domain.model.Invoice
import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.model.SnapToken
import com.subot.core.domain.model.TransactionHistory
import com.subot.core.domain.result.ApiResult

interface FinanceRepository {
    suspend fun getInvoices(): ApiResult<List<Invoice>>
    suspend fun getPaymentHistory(page: Int = 1, perPage: Int = 10): ApiResult<PaginatedData<TransactionHistory>>
    suspend fun requestSnapToken(tagihanId: Int): ApiResult<SnapToken>
}
