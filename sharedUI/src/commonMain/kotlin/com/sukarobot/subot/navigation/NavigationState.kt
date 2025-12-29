package com.sukarobot.subot.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

class NavigationState(
    val initialStartRoute: NavKey,
    var startRoute: NavKey,
    val loginRoute: NavKey,
    topLevelRoute: MutableState<NavKey>,
    isLoggedIn: MutableState<Boolean>,
    val backStacks: Map<NavKey, NavBackStack<NavKey>>
) {
    var topLevelRoute by topLevelRoute
    var isLoggedIn by isLoggedIn

    // Track the route user tried to access before being redirected to login
    var onLoginSuccessRoute: NavKey? by mutableStateOf(null)

    val stacksInUse: List<NavKey>
        get() = if (topLevelRoute == startRoute) {
            listOf(startRoute)
        } else {
            listOf(startRoute, topLevelRoute)
        }
}

@Composable
fun rememberNavigationState(
    startRoute: NavKey,
    loginRoute: NavKey = AppRoute.Login,
    topLevelRoutes: Set<NavKey>,
    initialIsLoggedIn: Boolean = false
): NavigationState {
    val topLevelRoute = rememberSerializable(
        startRoute,
        topLevelRoutes,
        configuration = serializersConfig,
        serializer = MutableStateSerializer(PolymorphicSerializer(NavKey::class))
    ) {
        mutableStateOf(startRoute)
    }

    val isLoggedIn = rememberSerializable(
        startRoute,
        topLevelRoutes,
        configuration = serializersConfig,
        serializer = MutableStateSerializer(Boolean.serializer())
    ) {
        mutableStateOf(initialIsLoggedIn)
    }

    val backStacks = topLevelRoutes.associateWith { key ->
        rememberNavBackStack(
            configuration = serializersConfig,
            key
        )
    }

    return remember(startRoute, loginRoute, topLevelRoute, isLoggedIn) {
        NavigationState(
            initialStartRoute = startRoute,
            startRoute = startRoute,
            loginRoute = loginRoute,
            topLevelRoute = topLevelRoute,
            isLoggedIn = isLoggedIn,
            backStacks = backStacks
        )
    }
}

val serializersConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            // Register all NavKey implementations here
            // e.g. subclass(AppRoute.Splash::class, AppRoute.Splash.serializer())
            subclass(AppRoute.Splash::class, AppRoute.Splash.serializer())
            subclass(AppRoute.Onboarding::class, AppRoute.Onboarding.serializer())
            subclass(AppRoute.Login::class, AppRoute.Login.serializer())
            subclass(AppRoute.Main::class, AppRoute.Main.serializer())
            subclass(AppRoute.Home::class, AppRoute.Home.serializer())
            subclass(AppRoute.Schedule::class, AppRoute.Schedule.serializer())
            subclass(AppRoute.Transaction::class, AppRoute.Transaction.serializer())
            subclass(AppRoute.Profile::class, AppRoute.Profile.serializer())
            subclass(AppRoute.Settings::class, AppRoute.Settings.serializer())
            subclass(AppRoute.SettingsDetail::class, AppRoute.SettingsDetail.serializer())
        }
    }
}

@Composable
fun NavigationState.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>> {
    val decoratedEntries = backStacks.mapValues { (_, stack) ->
        val decorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
            rememberViewModelStoreNavEntryDecorator()
        )
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = decorators,
            entryProvider = entryProvider
        )
    }

    return stacksInUse
        .flatMap { decoratedEntries[it] ?: emptyList() }
        .toMutableStateList()
}
