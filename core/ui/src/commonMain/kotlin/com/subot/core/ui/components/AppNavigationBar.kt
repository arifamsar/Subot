package com.subot.core.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.subot.core.ui.navigation.TOP_LEVEL_DESTINATIONS
import org.jetbrains.compose.resources.stringResource

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