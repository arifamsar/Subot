package com.subot.transactions.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.Navigator
import com.subot.core.ui.navigation.Route
import com.subot.transactions.TransactionScreen

fun EntryProviderScope<NavKey>.transactionFlow(
    navigator: Navigator,
) {
    entry<Route.Transaction> {
        TransactionScreen()
    }
}
