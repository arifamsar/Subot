package com.subot.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * A reusable dropdown field component that visually matches [AppTextField].
 *
 * @param T The type of items in the dropdown list.
 * @param selectedItem The currently selected item, or null if nothing is selected.
 * @param items The list of items to display in the dropdown menu.
 * @param onItemSelected Called when the user selects an item from the dropdown.
 * @param itemLabel A lambda that converts an item of type [T] to a display string.
 * @param label The field label text.
 * @param modifier Modifier applied to the root layout.
 * @param placeholder Placeholder text shown when no item is selected.
 * @param leadingIcon Optional leading icon displayed in the text field.
 * @param expanded Whether the dropdown menu is currently expanded.
 * @param onExpandedChange Called when the expanded state should change.
 * @param isError Whether the field is in an error state.
 * @param errorMessage The error message to display below the field when [isError] is true.
 * @param enabled Whether the field is enabled for interaction.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AppDropdownField(
    selectedItem: T?,
    items: List<T>,
    onItemSelected: (T) -> Unit,
    itemLabel: (T) -> String,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    leadingIcon: ImageVector? = null,
    expanded: Boolean = false,
    onExpandedChange: (Boolean) -> Unit = {},
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { onExpandedChange(!expanded) }
        ) {
            OutlinedTextField(
                value = selectedItem?.let { itemLabel(it) } ?: "",
                onValueChange = {},
                readOnly = true,
                placeholder = if (placeholder.isNotEmpty()) {
                    {
                        Text(
                            placeholder,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        )
                    }
                } else null,
                leadingIcon = leadingIcon?.let {
                    {
                        Icon(
                            imageVector = it,
                            contentDescription = null,
                            tint = if (isError) MaterialTheme.colorScheme.error
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                isError = isError,
                singleLine = true,
                enabled = enabled,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Transparent,
                    errorBorderColor = MaterialTheme.colorScheme.error,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedChange(false) }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = itemLabel(item),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        onClick = {
                            onItemSelected(item)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        if (isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

