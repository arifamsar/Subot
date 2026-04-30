package com.subot.core.domain.usecase

import com.subot.core.domain.model.PaginatedData
import com.subot.core.domain.model.TransactionHistory
import com.subot.core.domain.repository.FinanceRepository
import com.subot.core.domain.result.ApiResult

class GetPaymentHistoryUseCase(
    private val financeRepository: FinanceRepository
) {
    suspend operator fun invoke(page: Int = 1, perPage: Int = 10): ApiResult<PaginatedData<TransactionHistory>> = 
        financeRepository.getPaymentHistory(page, perPage)
}
