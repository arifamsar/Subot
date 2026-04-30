package com.subot.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InvoiceDto(
    @SerialName("id")
    val id: Int,
    @SerialName("invoice_number")
    val invoiceNumber: String,
    @SerialName("amount")
    val amount: Long,
    @SerialName("due_date")
    val dueDate: String,
    @SerialName("status")
    val status: String,
    @SerialName("description")
    val description: String? = null
)

@Serializable
data class TransactionHistoryDto(
    @SerialName("id")
    val id: Int,
    @SerialName("nomor_tagihan")
    val nomorTagihan: String,
    @SerialName("total_tagihan")
    val totalTagihan: Long,
    @SerialName("status")
    val status: String,
    @SerialName("jatuh_tempo")
    val jatuhTempo: String,
    @SerialName("tipe_tagihan")
    val tipeTagihan: String,
    @SerialName("program")
    val program: String
)

@Serializable
data class SnapTokenDto(
    @SerialName("snap_token")
    val snapToken: String,
    @SerialName("redirect_url")
    val redirectUrl: String? = null
)

@Serializable
data class SnapTokenRequestDto(
    @SerialName("tagihan_id")
    val tagihanId: Int
)

@Serializable
data class PaginatedFinanceDto<T>(
    @SerialName("items")
    val items: List<T>,
    @SerialName("pagination")
    val pagination: PaginationDto
)
