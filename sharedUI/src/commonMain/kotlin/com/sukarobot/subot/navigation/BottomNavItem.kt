package com.sukarobot.subot.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
)

val TOP_LEVEL_DESTINATIONS: Map<NavKey, BottomNavItem> = mapOf(
    AppRoute.Home to BottomNavItem(
        label = "Home",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
    ),
    AppRoute.Schedule to BottomNavItem(
        label = "Schedule",
        icon = Icons.Outlined.CalendarMonth,
        selectedIcon = Icons.Filled.CalendarMonth,
    ),
    AppRoute.Transaction to BottomNavItem(
        label = "Transaction",
        icon = Icons.Outlined.Receipt,
        selectedIcon = Icons.Filled.Receipt,
    ),
    AppRoute.Profile to BottomNavItem(
        label = "Profile",
        icon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
    )
)