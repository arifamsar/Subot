package com.subot.core.ui.components

import androidx.compose.runtime.Composable

@Composable
expect fun rememberImagePicker(onImagePicked: (ByteArray, String) -> Unit): ImagePickerLauncher

expect class ImagePickerLauncher {
    fun launch()
}
