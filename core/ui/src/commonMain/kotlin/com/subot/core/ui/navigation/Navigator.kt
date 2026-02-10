package com.subot.core.ui.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val state: NavigationState) {

    fun navigate(route: NavKey) {
        if(route in state.backStacks.keys) {
            state.topLevelRoute = route
        } else {
            state.backStacks[state.topLevelRoute]?.add(route)
        }
    }

    /**
     * Navigates to a detail route, removing any existing detail routes of the same type first.
     * This is useful in list-detail scenarios where you want to replace the current detail view
     * rather than stacking multiple detail views.
     *
     * @param T The type of the detail route (must extend [NavKey])
     * @param route The detail route to navigate to
     */
    inline fun <reified T : NavKey> navigateToDetail(route: T) {
        state.backStacks[state.topLevelRoute]?.addDetail(route)
    }

    fun goBack() {
        val currentStack = state.backStacks[state.topLevelRoute]
            ?: error("Back stack for ${state.topLevelRoute} doesn't exist")
        val currentRoute = currentStack.last()

        if(currentRoute == state.topLevelRoute) {
            state.topLevelRoute = state.startRoute
        } else {
            currentStack.removeLastOrNull()
        }
    }
}