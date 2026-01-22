package com.subot.core.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.components.icons.CalenderFilled
import com.subot.core.ui.components.icons.CalenderOutlined
import com.subot.core.ui.components.icons.Hicon
import com.subot.core.ui.components.icons.HomeFilled
import com.subot.core.ui.components.icons.HomeOutlined
import com.subot.core.ui.components.icons.ProfileFilled
import com.subot.core.ui.components.icons.ProfileOutlined
import com.subot.core.ui.components.icons.WalletFilled
import com.subot.core.ui.components.icons.WalletOutlined
import org.jetbrains.compose.resources.StringResource
import subot.core.ui.generated.resources.Res
import subot.core.ui.generated.resources.home
import subot.core.ui.generated.resources.profile
import subot.core.ui.generated.resources.schedule
import subot.core.ui.generated.resources.transaction

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
    Route.Home to BottomNavItem(
        label = "Home",
        icon = Hicon.HomeOutlined,
        selectedIcon = Hicon.HomeFilled,
    ),
    Route.Schedule to BottomNavItem(
        label = "Schedule",
        icon = Hicon.CalenderOutlined,
        selectedIcon = Hicon.CalenderFilled,
    ),
    Route.Transaction to BottomNavItem(
        label = "Transaction",
        icon = Hicon.WalletOutlined,
        selectedIcon = Hicon.WalletFilled,
    ),
    Route.Profile to BottomNavItem(
        label = "Profile",
        icon = Hicon.ProfileOutlined,
        selectedIcon = Hicon.ProfileFilled,
    )
)