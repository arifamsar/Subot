package com.sukarobot.subot.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.sukarobot.subot.ui.components.icons.CalenderFilled
import com.sukarobot.subot.ui.components.icons.CalenderOutlined
import com.sukarobot.subot.ui.components.icons.Hicon
import com.sukarobot.subot.ui.components.icons.HomeFilled
import com.sukarobot.subot.ui.components.icons.HomeOutlined
import com.sukarobot.subot.ui.components.icons.ProfileFilled
import com.sukarobot.subot.ui.components.icons.ProfileOutlined
import com.sukarobot.subot.ui.components.icons.WalletFilled
import com.sukarobot.subot.ui.components.icons.WalletOutlined

/**
 * Data class representing a bottom navigation item
 */
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
)

/**
 * Mapping of top-level destinations to their corresponding BottomNavItem
 */
val TOP_LEVEL_DESTINATIONS: Map<NavKey, BottomNavItem> = mapOf(
    AppRoute.Home to BottomNavItem(
        label = "Home",
        icon = Hicon.HomeOutlined,
        selectedIcon = Hicon.HomeFilled,
    ),
    AppRoute.Schedule to BottomNavItem(
        label = "Schedule",
        icon = Hicon.CalenderOutlined,
        selectedIcon = Hicon.CalenderFilled,
    ),
    AppRoute.Transaction to BottomNavItem(
        label = "Transaction",
        icon = Hicon.WalletOutlined,
        selectedIcon = Hicon.WalletFilled,
    ),
    AppRoute.Profile to BottomNavItem(
        label = "Profile",
        icon = Hicon.ProfileOutlined,
        selectedIcon = Hicon.ProfileFilled,
    )
)