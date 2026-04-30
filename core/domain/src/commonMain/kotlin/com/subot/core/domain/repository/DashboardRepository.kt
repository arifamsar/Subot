package com.subot.core.domain.repository

import com.subot.core.domain.model.Dashboard
import com.subot.core.domain.result.ApiResult

interface DashboardRepository {
    suspend fun getDashboard(): ApiResult<Dashboard>
}
