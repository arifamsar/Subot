package com.subot.profile.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.subot.core.domain.AppLanguage
import com.subot.core.ui.components.AppDialog
import com.subot.core.ui.components.AppLoadingIndicator
import com.subot.core.ui.components.icons.FAQCircle
import com.subot.core.ui.components.icons.Global
import com.subot.core.ui.components.icons.Hicon
import com.subot.core.ui.components.icons.Logout
import com.subot.core.ui.components.icons.MoonOutlined
import com.subot.core.ui.components.icons.Notification3
import com.subot.core.ui.components.icons.ProfileOutlined
import com.subot.core.ui.components.icons.SecuritySafe
import com.subot.core.ui.components.icons.Setting
import com.subot.core.ui.navigation.Route
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import subot.core.ui.generated.resources.Res
import subot.core.ui.generated.resources.app_settings
import subot.core.ui.generated.resources.app_settings_subtitle
import subot.core.ui.generated.resources.cancel
import subot.core.ui.generated.resources.dark_mode
import subot.core.ui.generated.resources.edit_profile
import subot.core.ui.generated.resources.edit_profile_subtitle
import subot.core.ui.generated.resources.english
import subot.core.ui.generated.resources.general_settings
import subot.core.ui.generated.resources.help_and_support
import subot.core.ui.generated.resources.indonesian
import subot.core.ui.generated.resources.language
import subot.core.ui.generated.resources.language_subtitle
import subot.core.ui.generated.resources.logout
import subot.core.ui.generated.resources.logout_confirmation_message
import subot.core.ui.generated.resources.notifications
import subot.core.ui.generated.resources.preferences
import subot.core.ui.generated.resources.profile
import subot.core.ui.generated.resources.security
import subot.core.ui.generated.resources.security_subtitle
import subot.core.ui.generated.resources.select_language
import subot.core.ui.generated.resources.support
import subot.core.ui.generated.resources.version_label

data class ProfileMenuItem(
    val title: StringResource,
    val subtitle: StringResource? = null,
    val icon: ImageVector,
    val hasSwitch: Boolean = false,
    val isDestructive: Boolean = false,
    val route: Route? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit = {},
    onNavigate: (Route) -> Unit = {}
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var notificationsEnabled by remember { mutableStateOf(true) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // Navigate out when logout is successful
    LaunchedEffect(uiState.isLogoutSuccessful) {
        if (uiState.isLogoutSuccessful) {
            onLogout()
        }
    }


    val generalSettings = remember {
        listOf(
            ProfileMenuItem(
                title = Res.string.edit_profile,
                subtitle = Res.string.edit_profile_subtitle,
                icon = Hicon.ProfileOutlined,
                route = Route.SettingsDetail("edit_profile")
            ),
            ProfileMenuItem(
                title = Res.string.security,
                subtitle = Res.string.security_subtitle,
                icon = Hicon.SecuritySafe,
                route = Route.SettingsDetail("security")
            ),
            ProfileMenuItem(
                title = Res.string.notifications,
                icon = Hicon.Notification3,
                hasSwitch = true
            )
        )
    }
    
    val preferences = remember {
        listOf(
            ProfileMenuItem(
                title = Res.string.language,
                subtitle = Res.string.language_subtitle,
                icon = Hicon.Global
            ),
            ProfileMenuItem(
                title = Res.string.dark_mode,
                icon = Hicon.MoonOutlined,
                hasSwitch = true
            ),
            ProfileMenuItem(
                title = Res.string.app_settings,
                subtitle = Res.string.app_settings_subtitle,
                icon = Hicon.Setting,
                route = Route.Settings
            )
        )
    }
    
    val support = remember {
        listOf(
            ProfileMenuItem(
                title = Res.string.help_and_support,
                icon = Hicon.FAQCircle,
                route = Route.SettingsDetail("help")
            ),
            ProfileMenuItem(
                title = Res.string.logout,
                icon = Hicon.Logout,
                isDestructive = true
            )
        )
    }
    
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Mitra",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stringResource(Res.string.profile),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
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
                        text = stringResource(Res.string.general_settings),
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
                        switchStates = mapOf(Res.string.notifications to notificationsEnabled),
                        onSwitchChange = { title, value ->
                            if (title == Res.string.notifications) notificationsEnabled = value
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
                        text = stringResource(Res.string.preferences),
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
                        switchStates = mapOf(Res.string.dark_mode to uiState.darkModeEnabled),
                        onSwitchChange = { title, value ->
                            if (title == Res.string.dark_mode) viewModel.onEvent(ProfileEvent.ToggleDarkMode(value))
                        },
                        onClick = { item ->
                            when (item.title) {
                                Res.string.language -> showLanguageDialog = true
                                else -> item.route?.let { onNavigate(it) }
                            }
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Support
                item {
                    Text(
                        text = stringResource(Res.string.support),
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
                            if (item.title == Res.string.logout) showLogoutDialog = true
                            else item.route?.let { onNavigate(it) }
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Version
                item {
                    Text(
                        text = stringResource(Res.string.version_label),
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
                    title = stringResource(Res.string.logout),
                    message = stringResource(Res.string.logout_confirmation_message),
                    confirmText = stringResource(Res.string.logout),
                    onConfirm = {
                        showLogoutDialog = false
                        viewModel.onEvent(ProfileEvent.Logout)
                    },
                    dismissText = stringResource(Res.string.cancel),
                    onDismiss = { showLogoutDialog = false }
                )
            }

            // Language Selection Dialog
            if (showLanguageDialog) {
                LanguageSelectionDialog(
                    selectedLanguage = uiState.selectedLanguage,
                    onLanguageSelected = { languageCode ->
                        viewModel.onEvent(ProfileEvent.SetLanguage(languageCode))
                        showLanguageDialog = false
                    },
                    onDismiss = { showLanguageDialog = false }
                )
            }

            // Loading overlay during logout
            if (uiState.isLoggingOut) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center
                ) {
                    AppLoadingIndicator(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
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
                    contentDescription = stringResource(Res.string.edit_profile),
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
    switchStates: Map<StringResource, Boolean> = emptyMap(),
    onSwitchChange: (StringResource, Boolean) -> Unit = { _, _ -> },
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
                text = stringResource(item.title),
                style = MaterialTheme.typography.bodyLarge,
                color = contentColor
            )
            if (item.subtitle != null) {
                Text(
                    text = stringResource(item.subtitle),
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

@Composable
private fun LanguageSelectionDialog(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AppDialog(
        title = stringResource(Res.string.select_language),
        confirmText = stringResource(Res.string.cancel),
        onConfirm = onDismiss,
        onDismiss = onDismiss,
        content = {
            Column {
                AppLanguage.entries.forEach { language ->
                    val languageName = when (language) {
                        AppLanguage.ENGLISH -> stringResource(Res.string.english)
                        AppLanguage.INDONESIAN -> stringResource(Res.string.indonesian)
                    }
                    
                    LanguageOption(
                        languageName = languageName,
                        isSelected = selectedLanguage == language.code,
                        onSelect = { onLanguageSelected(language.code) }
                    )
                }
            }
        }
    )
}

@Composable
private fun LanguageOption(
    languageName: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                role = Role.RadioButton,
                onClick = onSelect
            )
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = languageName,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
