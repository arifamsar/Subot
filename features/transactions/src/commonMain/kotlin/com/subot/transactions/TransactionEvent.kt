package com.subot.transactions

sealed class TransactionEvent {
    object LoadInvoices : TransactionEvent()
    object LoadPaymentHistory : TransactionEvent()
    data class RequestSnapToken(val tagihanId: Int) : TransactionEvent()
    object Refresh : TransactionEvent()
    object ClearSnapToken : TransactionEvent()
}
