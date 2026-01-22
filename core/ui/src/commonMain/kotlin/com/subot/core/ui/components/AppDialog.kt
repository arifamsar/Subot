package com.subot.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

/**
 * A styled dialog used for alerts, confirmations, and general prompts.
 */
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppDialog(
    title: String,
    message: String,
    confirmText: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)? = null,
    dismissText: String? = null,
    icon: ImageVector? = null,
    dismissOnClickOutside: Boolean = true,
    dismissOnBackPress: Boolean = true
) {
    AlertDialog(
        onDismissRequest = { onDismiss?.invoke() }, modifier = modifier, icon = icon?.let {
        {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }, title = {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }, text = {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp)
        )
    }, confirmButton = {
        TextButton(
            shapes = ButtonDefaults.shapes(), onClick = onConfirm
        ) {
            Text(
                text = confirmText,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }, dismissButton = dismissText?.let {
        @Composable {
            TextButton(
                shapes = ButtonDefaults.shapes(), onClick = { onDismiss?.invoke() }) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }, shape = RoundedCornerShape(16.dp), tonalElevation = 8.dp, properties = DialogProperties(
        dismissOnBackPress = dismissOnBackPress, dismissOnClickOutside = dismissOnClickOutside
    )
    )
}

/**
 * Convenience dialog for simple info/acknowledge cases.
 */
@Composable
fun AppInfoDialog(
    title: String,
    message: String,
    confirmText: String = "OK",
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    AppDialog(
        title = title,
        message = message,
        confirmText = confirmText,
        onConfirm = onConfirm,
        modifier = modifier,
        onDismiss = null,
        dismissText = null,
        icon = icon,
        dismissOnBackPress = false,
        dismissOnClickOutside = false
    )
}

@Preview
@Composable
private fun AppDialogPreview() {
    AppDialog(
        title = "Delete item?",
        message = "This action cannot be undone. Are you sure you want to proceed?",
        confirmText = "Delete",
        dismissText = "Cancel",
        onConfirm = {},
        onDismiss = {})
}
