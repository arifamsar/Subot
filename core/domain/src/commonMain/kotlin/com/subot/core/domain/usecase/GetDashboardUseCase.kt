package com.subot.core.domain.usecase

import com.subot.core.domain.model.Dashboard
import com.subot.core.domain.repository.DashboardRepository
import com.subot.core.domain.result.ApiResult

class GetDashboardUseCase(
    private val dashboardRepository: DashboardRepository
) {
    suspend operator fun invoke(): ApiResult<Dashboard> = dashboardRepository.getDashboard()
}
