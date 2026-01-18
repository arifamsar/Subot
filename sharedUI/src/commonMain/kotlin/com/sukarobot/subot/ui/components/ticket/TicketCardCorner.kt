package com.sukarobot.subot.ui.components.ticket

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Corner configuration for TicketCard.
 */
data class TicketCardCorner(
    val topLeft: Dp = 25.dp,
    val topRight: Dp = 25.dp,
    val bottomRight: Dp = 25.dp,
    val bottomLeft: Dp = 25.dp
)