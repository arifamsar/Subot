package com.subot.core.ui.components

import androidx.compose.runtime.Composable

@Composable
expect fun rememberPlatformPdfHelper(): PlatformPdfHelper

expect class PlatformPdfHelper {
    fun saveAndOpenPdf(fileName: String, bytes: ByteArray)
}
