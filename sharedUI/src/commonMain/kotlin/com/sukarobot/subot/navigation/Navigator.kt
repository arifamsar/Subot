package com.sukarobot.subot.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val state: NavigationState) {

    fun navigate(route: NavKey) {
        // Check if route requires login and user is not logged in
        if (route is AppRoute.RequiresLogin && !state.isLoggedIn) {
            // Store the intended destination and redirect to login
            state.onLoginSuccessRoute = route
            if (state.loginRoute in state.backStacks.keys) {
                state.topLevelRoute = state.loginRoute
            } else {
                state.backStacks[state.topLevelRoute]?.add(state.loginRoute)
            }
            return
        }

        // If user explicitly navigates to login, clear the success route
        if (route == state.loginRoute) {
            state.onLoginSuccessRoute = null
        }

        if (route in state.backStacks.keys) {
            state.topLevelRoute = route
        } else {
            state.backStacks[state.topLevelRoute]?.add(route)
        }
    }

    fun goBack() {
        val currentStack = state.backStacks[state.topLevelRoute]
            ?: error("No back stack for route ${state.topLevelRoute}")
        val currentRoute = currentStack.last()

        // Prevent back navigation from login/onboarding when not logged in
        if (!state.isLoggedIn && (currentRoute == state.loginRoute || currentRoute == AppRoute.Onboarding)) {
            // User is on login/onboarding screen and not logged in - don't allow back navigation
            return
        }

        if (currentRoute == state.topLevelRoute) {
            // If we're at a top-level route and it's the start route, we can't go back further
            if (state.topLevelRoute == state.startRoute) {
                return
            }
            state.topLevelRoute = state.startRoute
        } else {
            currentStack.removeLastOrNull()
        }
    }

    /**
     * Call this when user successfully logs in
     */
    fun onLoginSuccess() {
        state.isLoggedIn = true

        // Determine the destination (intended route or default to Main)
        val destination = state.onLoginSuccessRoute ?: AppRoute.Main
        state.onLoginSuccessRoute = null

        // Clear login from all stacks first
        state.backStacks.values.forEach { stack ->
            stack.removeAll { it == state.loginRoute }
        }

        // Ensure each top-level stack has at least its root route
        state.backStacks.forEach { (route, stack) ->
            if (stack.isEmpty()) {
                stack.add(route)
            }
        }

        // Switch root to Main so back exits the app
        state.startRoute = AppRoute.Main
        state.topLevelRoute = AppRoute.Main

        // Navigate to destination
        if (destination in state.backStacks.keys) {
            state.topLevelRoute = destination
        } else {
            state.backStacks[state.topLevelRoute]?.add(destination)
        }
    }

    /**
     * Call this when user logs out
     */
    fun logout() {
        state.isLoggedIn = false
        state.onLoginSuccessRoute = null

        // Clear ALL back stacks completely to prevent any back navigation
        state.backStacks.values.forEach { stack ->
            stack.clear()
        }

        // Reset root back to the login screen directly
        state.startRoute = state.loginRoute

        // Navigate directly to login screen with a fresh stack
        val loginStack = state.backStacks[state.loginRoute]
        if (loginStack != null) {
            loginStack.clear()
            loginStack.add(state.loginRoute)
            state.topLevelRoute = state.loginRoute
        } else {
            // Fallback: if login is not a top-level route, use initial start route
            state.startRoute = state.initialStartRoute
            val startStack = state.backStacks[state.initialStartRoute]
            if (startStack != null) {
                startStack.clear()
                startStack.add(state.initialStartRoute)
                state.topLevelRoute = state.initialStartRoute
            }
        }
    }
}