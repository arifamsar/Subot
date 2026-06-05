package com.subot.transactions

import com.subot.core.domain.model.Invoice
import com.subot.core.domain.model.SnapToken
import com.subot.core.domain.model.TransactionHistory

data class TransactionUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val invoices: List<Invoice> = emptyList(),
    val paymentHistory: List<TransactionHistory> = emptyList(),
    val snapToken: SnapToken? = null,
    val error: String? = null,
    val userRole: String? = null
)
