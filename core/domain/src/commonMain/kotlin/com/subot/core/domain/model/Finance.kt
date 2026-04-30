package com.subot.core.domain.model

data class Invoice(
    val id: Int,
    val invoiceNumber: String,
    val amount: Long,
    val dueDate: String,
    val status: String,
    val description: String?
)

data class TransactionHistory(
    val id: Int,
    val invoiceNumber: String,
    val amount: Long,
    val paymentDate: String,
    val paymentMethod: String?,
    val status: String
)

data class SnapToken(
    val snapToken: String,
    val redirectUrl: String?
)
