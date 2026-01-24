package com.subot.core.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.isLiquidEnabled
import com.subot.core.ui.navigation.TOP_LEVEL_DESTINATIONS
import io.github.fletchmckee.liquid.LiquidState
import io.github.fletchmckee.liquid.liquid
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Height of the floating navigation bar including padding.
 * Use this value for bottom content padding in scrollable screens.
 */
val FloatingNavBarHeight: Dp = 120.dp

/**
 * Content padding to use in LazyColumn/LazyRow for screens with floating navbar.
 * Includes top padding for status bar area and bottom padding for floating navbar.
 */
fun floatingNavBarContentPadding(
    top: Dp = 16.dp,
    bottom: Dp = FloatingNavBarHeight
): PaddingValues = PaddingValues(top = top, bottom = bottom)

@Composable
fun SubotNavigationBar(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (topLevelDestination, data) ->
            val selected = topLevelDestination == selectedKey
            val label = stringResource(data.label)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onSelectKey(topLevelDestination)
                },
                icon = {
                    Icon(
                        imageVector = if (selected) data.selectedIcon else data.icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(
                        text = label,
                    )
                }
            )
        }
    }
}

@Composable
fun SubotNavigationRail(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (topLevelDestination, data) ->
            val selected = topLevelDestination == selectedKey
            val label = stringResource(data.label)
            NavigationRailItem(
                selected = selected,
                onClick = {
                    onSelectKey(topLevelDestination)
                },
                icon = {
                    Icon(
                        imageVector = if (selected) data.selectedIcon else data.icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(
                        text = label,
                    )
                }
            )
        }
    }
}

/**
 * iOS-style Liquid Glass Navigation Bottom Bar
 *
 * A translucent navigation bar with frosted glass effect similar to iOS liquid glass design.
 * Features animated selection indicator, blur effect, and subtle refraction.
 *
 * @param selectedKey Currently selected navigation key
 * @param onSelectKey Callback when a navigation item is selected
 * @param modifier Modifier to apply to the navigation bar
 */
@Composable
fun NavigationLiquidBottomBar(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    val liquidState = LocalBottomNavigationLiquid.current
    val shape = RoundedCornerShape(28.dp)

    Box(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = shape,
                ambientColor = Color.Black.copy(alpha = 0.1f),
                spotColor = Color.Black.copy(alpha = 0.15f)
            )
            .clip(shape)
            .background(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.72f)
            )
            .then(
                if (isLiquidEnabled()) {
                    Modifier.liquid(liquidState) {
                        this.shape = shape
                        this.frost = 4.dp
                        this.curve = 0.35f
                        this.refraction = 0.08f
                        this.dispersion = 0.15f
                        this.saturation = 0.6f
                    }
                } else Modifier
            )
            .pointerInput(Unit) {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TOP_LEVEL_DESTINATIONS.forEach { (destination, data) ->
                val selected = destination == selectedKey

                LiquidNavItem(
                    selected = selected,
                    onClick = { onSelectKey(destination) },
                    icon = if (selected) data.selectedIcon else data.icon,
                    label = data.label,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

/**
 * Individual navigation item for the liquid glass bottom bar
 */
@Composable
private fun LiquidNavItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: StringResource,
    modifier: Modifier = Modifier
) {
    val labelText = stringResource(label)

    val contentColor by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "contentColor"
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                role = Role.Tab,
                onClick = onClick
            )
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon with animated color
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Icon(
                imageVector = icon,
                contentDescription = labelText,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Label with animated color
        Text(
            text = labelText,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                fontSize = 11.sp
            ),
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Legacy liquid glass bottom bar - kept for backward compatibility
 * @see NavigationLiquidBottomBar for the improved version
 */
@Composable
fun LiquidGlassBottomBar(
    modifier: Modifier = Modifier,
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit
) {
    val liquidState = LocalBottomNavigationLiquid.current
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            .then(
                if (isLiquidEnabled()) {
                    Modifier.liquid(liquidState) {
                        shape = CircleShape
                        this.frost = 16.dp
                        this.curve = .4f
                        this.refraction = .1f
                        this.dispersion = .2f
                        this.saturation = .5f
                    }
                } else Modifier
            )
            .pointerInput(Unit) {}
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (topLevelDestination, data) ->
            val selected = topLevelDestination == selectedKey
            val label = stringResource(data.label)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onSelectKey(topLevelDestination)
                },
                icon = {
                    Icon(
                        imageVector = if (selected) data.selectedIcon else data.icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(
                        text = label,
                    )
                }
            )
        }
    }
}

val LocalBottomNavigationLiquid = compositionLocalOf<LiquidState> {
    error("State not declared")
}
