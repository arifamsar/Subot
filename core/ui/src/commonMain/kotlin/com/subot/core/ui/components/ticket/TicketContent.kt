package com.subot.core.ui.components.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * A layout helper for TicketCard that splits its content into two logical sections.
 *
 * - [topContent] is placed above the dashed divider.
 *   Typically used for the main information such as title, event details, or branding.
 *
 * - [bottomContent] is placed below the dashed divider.
 *   Commonly used for secondary info such as QR codes, barcodes, or additional instructions.
 *
 * This structure mirrors real-world tickets/coupons, making it intuitive for developers and users.
 */
@Composable
fun TicketContent(
    modifier: Modifier = Modifier,
    notchWeight: Float = 0.7f,
    topContent: @Composable () -> Unit = {},
    bottomContent: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(notchWeight)
                .background(Color.Transparent)
                .padding()
        ) {
            topContent()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f-notchWeight)
                .background(Color.Transparent)
        ) {
            bottomContent()
        }
    }
}