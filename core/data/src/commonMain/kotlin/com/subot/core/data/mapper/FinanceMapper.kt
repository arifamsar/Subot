package com.subot.core.data.mapper

import com.subot.core.data.dto.InvoiceDto
import com.subot.core.data.dto.SnapTokenDto
import com.subot.core.data.dto.TransactionHistoryDto
import com.subot.core.domain.model.Invoice
import com.subot.core.domain.model.SnapToken
import com.subot.core.domain.model.TransactionHistory

fun InvoiceDto.toDomain(): Invoice = Invoice(
    id = id,
    invoiceNumber = invoiceNumber,
    amount = amount,
    dueDate = dueDate,
    status = status,
    description = description
)

fun TransactionHistoryDto.toDomain(): TransactionHistory = TransactionHistory(
    id = id,
    invoiceNumber = invoiceNumber,
    amount = amount,
    paymentDate = paymentDate,
    paymentMethod = paymentMethod,
    status = status
)

fun SnapTokenDto.toDomain(): SnapToken = SnapToken(
    snapToken = snapToken,
    redirectUrl = redirectUrl
)
