package com.subot.core.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
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

enum class TopLevelDestination(
    val label: StringResource,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val route: Route
) {
    HOME(
        label = Res.string.home,
        icon = Hicon.HomeOutlined,
        selectedIcon = Hicon.HomeFilled,
        route = Route.Home
    ),
    SCHEDULE(
        label = Res.string.schedule,
        icon = Hicon.CalenderOutlined,
        selectedIcon = Hicon.CalenderFilled,
        route = Route.Schedule
    ),
    TRANSACTION(
        label = Res.string.transaction,
        icon = Hicon.WalletOutlined,
        selectedIcon = Hicon.WalletFilled,
        route = Route.Transaction
    ),
    PROFILE(
        label = Res.string.profile,
        icon = Hicon.ProfileOutlined,
        selectedIcon = Hicon.ProfileFilled,
        route = Route.Profile
    )
}