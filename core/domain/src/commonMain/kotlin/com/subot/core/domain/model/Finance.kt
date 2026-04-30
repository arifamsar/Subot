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
    val nomorTagihan: String,
    val totalTagihan: Long,
    val status: String,
    val jatuhTempo: String,
    val tipeTagihan: String,
    val program: String
)

data class SnapToken(
    val snapToken: String,
    val redirectUrl: String?
)
