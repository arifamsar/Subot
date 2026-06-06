package com.subot.profile.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.subot.core.ui.components.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AppTopBar(
            title = "Settings",
            onNavigationClick = onBack,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text("General Settings Screen")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDetailScreen(
    modifier: Modifier = Modifier,
    settingId: String,
    onBack: () -> Unit
) {
    val title = when (settingId) {
        "notifications" -> "Notification Settings"
        "edit_profile" -> "Edit Profile"
        "security" -> "Security Settings"
        else -> "Setting Detail"
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AppTopBar(
            title = title,
            onNavigationClick = onBack,
        )
        if (settingId == "notifications") {
            var pushNotificationsEnabled by remember { mutableStateOf(true) }
            var emailNotificationsEnabled by remember { mutableStateOf(false) }
            var updateNotificationsEnabled by remember { mutableStateOf(true) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Configure your notifications preferences below.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Push Notifications",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Receive notifications on this device",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = pushNotificationsEnabled,
                        onCheckedChange = { pushNotificationsEnabled = it }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Email Notifications",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Receive notifications via email",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = emailNotificationsEnabled,
                        onCheckedChange = { emailNotificationsEnabled = it }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Updates & Announcements",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Receive updates about new features and schedules",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Switch(
                        checked = updateNotificationsEnabled,
                        onCheckedChange = { updateNotificationsEnabled = it }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Details for $settingId",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "This is a nested screen inside Profile tab",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}