package com.subot.transactions.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import com.subot.core.ui.navigation.Route
import com.subot.transactions.TransactionScreen

fun EntryProviderScope<Route>.transactionFlow(
    rootBackStack: SnapshotStateList<Route>,
    sharedTransitionScope: SharedTransitionScope
) {
    entry<Route.Transaction> {
        TransactionScreen()
    }
}
