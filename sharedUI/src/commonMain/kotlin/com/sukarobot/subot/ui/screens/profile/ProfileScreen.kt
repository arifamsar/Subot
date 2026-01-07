package com.sukarobot.subot.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sukarobot.subot.navigation.AppRoute
import com.sukarobot.subot.ui.components.AppDialog
import com.sukarobot.subot.ui.components.icons.FAQCircle
import com.sukarobot.subot.ui.components.icons.Hicon
import com.sukarobot.subot.ui.components.icons.Logout
import com.sukarobot.subot.ui.components.icons.MoonOutlined
import com.sukarobot.subot.ui.components.icons.Notification3
import com.sukarobot.subot.ui.components.icons.ProfileOutlined
import com.sukarobot.subot.ui.components.icons.SecuritySafe
import com.sukarobot.subot.ui.components.icons.Setting
import org.koin.compose.viewmodel.koinViewModel

data class ProfileMenuItem(
    val title: String,
    val subtitle: String? = null,
    val icon: ImageVector,
    val hasSwitch: Boolean = false,
    val isDestructive: Boolean = false,
    val route: AppRoute? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {},
    onNavigate: (AppRoute) -> Unit = {}
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val darkModeEnabled by viewModel.darkModeEnabled.collectAsState()
    var notificationsEnabled by remember { mutableStateOf(true) }
    var showLogoutDialog by remember { mutableStateOf(false) }


    val generalSettings = remember {
        listOf(
            ProfileMenuItem(
                title = "Edit Profile",
                subtitle = "Update your personal information",
                icon = Hicon.ProfileOutlined,
                route = AppRoute.SettingsDetail("edit_profile")
            ),
            ProfileMenuItem(
                title = "Security",
                subtitle = "Password and authentication",
                icon = Hicon.SecuritySafe,
                route = AppRoute.SettingsDetail("security")
            ),
            ProfileMenuItem(
                title = "Notifications",
                icon = Hicon.Notification3,
                hasSwitch = true
            )
        )
    }
    
    val preferences = remember {
        listOf(
            ProfileMenuItem(
                title = "Dark Mode",
                icon = Hicon.MoonOutlined,
                hasSwitch = true
            ),
            ProfileMenuItem(
                title = "App Settings",
                subtitle = "Language, currency, and more",
                icon = Hicon.Setting,
                route = AppRoute.Settings
            )
        )
    }
    
    val support = remember {
        listOf(
            ProfileMenuItem(
                title = "Help & Support",
                icon = Hicon.FAQCircle,
                route = AppRoute.SettingsDetail("help")
            ),
            ProfileMenuItem(
                title = "Logout",
                icon = Hicon.Logout,
                isDestructive = true
            )
        )
    }
    
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // Header
            item {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            // Profile Card
            item {
                ProfileCard(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // General Settings
            item {
                Text(
                    text = "General",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SettingsGroup(
                    items = generalSettings,
                    switchStates = mapOf("Notifications" to notificationsEnabled),
                    onSwitchChange = { title, value ->
                        if (title == "Notifications") notificationsEnabled = value
                    },
                    onClick = { item ->
                        item.route?.let { onNavigate(it) }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Preferences
            item {
                Text(
                    text = "Preferences",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SettingsGroup(
                    items = preferences,
                    switchStates = mapOf("Dark Mode" to darkModeEnabled),
                    onSwitchChange = { title, value ->
                        if (title == "Dark Mode") viewModel.toggleDarkMode(value)
                    },
                    onClick = { item ->
                        item.route?.let { onNavigate(it) }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Support
            item {
                Text(
                    text = "Support",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                SettingsGroup(
                    items = support,
                    onClick = { item ->
                        if (item.title == "Logout") showLogoutDialog = true
                        else item.route?.let { onNavigate(it) }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Version
            item {
                Text(
                    text = "Version 1.0.0",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }

        // Logout Confirmation Dialog
        if (showLogoutDialog) {
            AppDialog(
                title = "Logout",
                message = "Are you sure you want to logout?",
                confirmText = "Logout",
                onConfirm = {
                    showLogoutDialog = false
                    viewModel.logout { onLogout() }
                },
                dismissText = "Cancel",
                onDismiss = { showLogoutDialog = false }
            )
        }
    }
}

@Composable
private fun ProfileCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "JD",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "John Doe",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "john.doe@email.com",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
            }
            
            IconButton(onClick = { /* Edit profile */ }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
private fun SettingsGroup(
    items: List<ProfileMenuItem>,
    modifier: Modifier = Modifier,
    switchStates: Map<String, Boolean> = emptyMap(),
    onSwitchChange: (String, Boolean) -> Unit = { _, _ -> },
    onClick: (ProfileMenuItem) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Column {
            items.forEachIndexed { index, item ->
                SettingsItem(
                    item = item,
                    switchState = switchStates[item.title],
                    onSwitchChange = { onSwitchChange(item.title, it) },
                    onClick = { onClick(item) }
                )
                if (index < items.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsItem(
    item: ProfileMenuItem,
    switchState: Boolean?,
    onSwitchChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    val contentColor = if (item.isDestructive)
        MaterialTheme.colorScheme.error
    else
        MaterialTheme.colorScheme.onSurface

    val rowModifier = if (item.hasSwitch && switchState != null) {
        Modifier
            .toggleable(
                value = switchState,
                role = Role.Switch,
                onValueChange = onSwitchChange
            )
            .fillMaxWidth()
            .padding(16.dp)
    } else {
        Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(16.dp)
    }

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                color = contentColor
            )
            if (item.subtitle != null) {
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        if (item.hasSwitch && switchState != null) {
            Switch(
                checked = switchState,
                onCheckedChange = onSwitchChange
            )
        } else {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
