package com.subot.core.domain.usecase

import com.subot.core.domain.model.SnapToken
import com.subot.core.domain.repository.FinanceRepository
import com.subot.core.domain.result.ApiResult

class RequestSnapTokenUseCase(
    private val financeRepository: FinanceRepository
) {
    suspend operator fun invoke(tagihanId: Int): ApiResult<SnapToken> = 
        financeRepository.requestSnapToken(tagihanId)
}
