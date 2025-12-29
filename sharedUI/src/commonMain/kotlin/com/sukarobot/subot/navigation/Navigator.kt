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

        if (currentRoute == state.topLevelRoute) {
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

        // Navigate to destination
        if (destination in state.backStacks.keys) {
            // Destination is a top-level route, just switch to it
            state.topLevelRoute = destination
        } else {
            // Destination is a nested route, add it to the current stack
            state.backStacks[state.topLevelRoute]?.add(destination)
        }
    }

    /**
     * Call this when user logs out
     */
    fun logout() {
        state.isLoggedIn = false
        state.onLoginSuccessRoute = null

        // Remove all routes that require login from all stacks
        state.backStacks.values.forEach { stack ->
            stack.removeAll { it is AppRoute.RequiresLogin }
        }

        // Ensure the startRoute stack has at least the login route
        val startStack = state.backStacks[state.startRoute]
        if (startStack != null) {
            if (startStack.isEmpty()) {
                startStack.add(state.startRoute)
            }
            // Add login if not already present
            if (state.loginRoute !in startStack) {
                startStack.add(state.loginRoute)
            }
            state.topLevelRoute = state.startRoute
        } else {
            // Fallback: login is a top-level route
            state.topLevelRoute = state.loginRoute
            state.backStacks[state.loginRoute]?.let { loginStack ->
                if (loginStack.isEmpty()) {
                    loginStack.add(state.loginRoute)
                }
            }
        }
    }
}