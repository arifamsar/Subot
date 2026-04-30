package com.subot.transactions.di

import com.subot.transactions.TransactionViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val transactionModule = module {
    viewModelOf(::TransactionViewModel)
}
