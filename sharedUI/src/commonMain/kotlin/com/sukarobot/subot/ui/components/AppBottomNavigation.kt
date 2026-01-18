package com.sukarobot.subot.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
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
    val items = TOP_LEVEL_DESTINATIONS.entries.toList()
    val selectedIndex = items.indexOfFirst { it.key == selectedRoute }.coerceAtLeast(0)
    val itemLayouts = remember { mutableStateListOf<ItemLayout?>().apply { repeat(items.size) { add(null) } } }
    val density = LocalDensity.current

    val indicatorOffset: Dp by animateDpAsState(
        targetValue = itemLayouts.getOrNull(selectedIndex)?.startPx?.let { with(density) { it.toDp() } } ?: 0.dp,
        label = "indicatorOffset"
    )
    val indicatorWidth: Dp by animateDpAsState(
        targetValue = itemLayouts.getOrNull(selectedIndex)?.widthPx?.let { with(density) { it.toDp() } } ?: 0.dp,
        label = "indicatorWidth"
    )

    val barShape = RoundedCornerShape(24.dp)
    val tint = MaterialTheme.colorScheme.surface.copy(alpha = 0.55f)
    val stroke = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    val highlight = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)

    Box(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .navigationBarsPadding()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(barShape)
                .background(tint)
//                .border(width = 1.dp, color = stroke, shape = barShape)
                .blur(28.dp)
        )

        // Elevated content layer above the blur container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(barShape)
                .background(tint)
//                .border(width = 1.dp, color = stroke, shape = barShape)
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            if (indicatorWidth > 0.dp) {
                Box(
                    modifier = Modifier
                        .height(44.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .height(40.dp)
                            .width(indicatorWidth)
                            .offset {
                                IntOffset(x = indicatorOffset.roundToPx(), y = 0)
                            }
                            .clip(RoundedCornerShape(18.dp))
                            .background(highlight)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, (route, item) ->
                    AnimatedBottomNavItem(
                        selected = index == selectedIndex,
                        onClick = { onRouteSelected(route) },
                        item = item,
                        onPositioned = { layout ->
                            if (index < itemLayouts.size) itemLayouts[index] = layout
                        }
                    )
                }
            }
        }
    }
}

private data class ItemLayout(val startPx: Float, val widthPx: Float)

@Composable
private fun RowScope.AnimatedBottomNavItem(
    selected: Boolean,
    onClick: () -> Unit,
    item: BottomNavItem,
    onPositioned: (ItemLayout) -> Unit
) {
    val iconColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
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
            .padding(vertical = 8.dp)
            .onGloballyPositioned { coordinates ->
                val bounds = coordinates.boundsInParent()
                onPositioned(ItemLayout(startPx = bounds.left, widthPx = bounds.width))
            },
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