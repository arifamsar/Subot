package com.subot.core.domain.usecase

import com.subot.core.domain.model.Invoice
import com.subot.core.domain.repository.FinanceRepository
import com.subot.core.domain.result.ApiResult

class GetInvoicesUseCase(
    private val financeRepository: FinanceRepository
) {
    suspend operator fun invoke(): ApiResult<List<Invoice>> = financeRepository.getInvoices()
}
