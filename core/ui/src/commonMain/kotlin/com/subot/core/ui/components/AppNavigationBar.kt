package com.subot.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailDefaults
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailItemDefaults
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberTooltipState
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.TOP_LEVEL_DESTINATIONS
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import subot.core.ui.generated.resources.Res
import subot.core.ui.generated.resources.collapse_rail
import subot.core.ui.generated.resources.collapsed
import subot.core.ui.generated.resources.expand_rail
import subot.core.ui.generated.resources.expanded

@Composable
fun SubotNavigationBar(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
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
                        tint = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground,
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SubotNavigationRail(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = rememberWideNavigationRailState()
    val scope = rememberCoroutineScope()
    val headerDescription =
        if (state.targetValue == WideNavigationRailValue.Expanded) {
            stringResource(Res.string.collapse_rail)
        } else {
            stringResource(Res.string.expand_rail)
        }

    WideNavigationRail(
        modifier = modifier,
        colors = WideNavigationRailDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background,
            modalContainerColor = MaterialTheme.colorScheme.background
        ),
        state = state,
        header = {
            TooltipBox(
                positionProvider =
                    TooltipDefaults.rememberTooltipPositionProvider(
                        TooltipAnchorPosition.Above
                    ),
                tooltip = { PlainTooltip { Text(headerDescription) } },
                state = rememberTooltipState(),
            ) {
                val expandedString = stringResource(Res.string.expanded)
                val collapsedString = stringResource(Res.string.collapsed)
                IconButton(
                    modifier =
                        Modifier.padding(start = 24.dp).semantics {
                            stateDescription =
                                if (state.currentValue == WideNavigationRailValue.Expanded) {
                                    expandedString
                                } else {
                                    collapsedString
                                }
                        },
                    shapes = IconButtonDefaults.shapes(),
                    onClick = {
                        scope.launch {
                            if (state.targetValue == WideNavigationRailValue.Expanded)
                                state.collapse()
                            else state.expand()
                        }
                    },
                ) {
                    if (state.targetValue == WideNavigationRailValue.Expanded) {
                        Icon(Icons.AutoMirrored.Filled.MenuOpen, headerDescription)
                    } else {
                        Icon(Icons.Filled.Menu, headerDescription)
                    }
                }
            }
        },
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (topLevelDestination, data) ->
            val selected = topLevelDestination == selectedKey
            val label = stringResource(data.label)
            WideNavigationRailItem(
                railExpanded = state.targetValue == WideNavigationRailValue.Expanded,
                selected = selected,
                colors = WideNavigationRailItemDefaults.colors(
                    selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer
                ),
                onClick = {
                    onSelectKey(topLevelDestination)
                },
                icon = {
                    Icon(
                        imageVector = if (selected) data.selectedIcon else data.icon,
                        tint = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground,
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