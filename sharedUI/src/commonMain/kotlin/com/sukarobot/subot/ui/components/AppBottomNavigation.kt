package com.sukarobot.subot.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.sukarobot.subot.navigation.BottomNavItem
import com.sukarobot.subot.navigation.TOP_LEVEL_DESTINATIONS

@Composable
fun AppBottomNavigation(
    selectedRoute: NavKey,
    onRouteSelected: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (route, item) ->
            AnimatedBottomNavItem(
                selected = route == selectedRoute,
                onClick = { onRouteSelected(route) },
                item = item
            )
        }
    }
}

@Composable
private fun RowScope.AnimatedBottomNavItem(
    selected: Boolean,
    onClick: () -> Unit,
    item: BottomNavItem
) {
    // Icon and text color animation
    val iconColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "iconColor"
    )

    Column(
        modifier = Modifier
            .weight(1f)
            .clickable(
                onClick = onClick,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
    ) {
        Icon(
            imageVector = if (selected) item.selectedIcon else item.icon,
            contentDescription = item.label,
            modifier = Modifier.size(24.dp),
            tint = iconColor
        )

        Text(
            text = item.label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = iconColor
        )
    }
}