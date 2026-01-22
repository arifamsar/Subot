package com.sukarobot.subot.ui.screens

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.subot.core.ui.navigation.Route
import com.subot.home.navigation.homeFlow
import com.subot.profile.navigation.profileFlow
import com.subot.schedule.navigation.scheduleFlow
import com.subot.transactions.navigation.transactionFlow
import com.sukarobot.subot.AppState

@Composable
fun MainScreen(
    appState: AppState,
    homeBackStack: SnapshotStateList<Route>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            SharedTransitionLayout {
                NavDisplay(
                    backStack = homeBackStack,
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = entryProvider {
                        homeFlow(
                            rootBackStack = homeBackStack,
                            sharedTransitionScope = this@SharedTransitionLayout
                        )
                        scheduleFlow(
                            rootBackStack = homeBackStack,
                            sharedTransitionScope = this@SharedTransitionLayout
                        )
                        transactionFlow(
                            rootBackStack = homeBackStack,
                            sharedTransitionScope = this@SharedTransitionLayout
                        )
                        profileFlow(
                            rootBackStack = homeBackStack,
                            sharedTransitionScope = this@SharedTransitionLayout,
                            onLogout = {
                                appState.navBackStack.clear()
                                appState.navBackStack.add(Route.Login)
                            }
                        )
                    }
                )
            }
        }

        BottomNavigation(
            appState = appState
        )
    }
}