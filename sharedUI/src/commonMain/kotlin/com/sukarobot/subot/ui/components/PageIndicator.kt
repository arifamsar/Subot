package com.sukarobot.subot.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
    indicatorSize: Dp = 8.dp,
    spacing: Dp = 8.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            
            val width by animateDpAsState(
                targetValue = if (isSelected) indicatorSize * 3 else indicatorSize,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                ),
                label = "width"
            )
            
            val color by animateColorAsState(
                targetValue = if (isSelected) activeColor else inactiveColor,
                label = "color"
            )
            
            Box(
                modifier = Modifier
                    .width(width)
                    .height(indicatorSize)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Composable
fun DotIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
    dotSize: Dp = 10.dp,
    activeDotSize: Dp = 12.dp,
    spacing: Dp = 8.dp
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            
            val size by animateDpAsState(
                targetValue = if (isSelected) activeDotSize else dotSize,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                ),
                label = "size"
            )
            
            val color by animateColorAsState(
                targetValue = if (isSelected) activeColor else inactiveColor,
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "color"
            )
            
            Box(
                modifier = Modifier
                    .width(size)
                    .height(size)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WaterDropIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
    dotSize: Dp = 10.dp,
    spacing: Dp = 8.dp
) {
    val density = LocalDensity.current
    val dotPx = with(density) { dotSize.toPx() }
    val spacingPx = with(density) { spacing.toPx() }

    val width = (pagerState.pageCount * dotPx) + ((pagerState.pageCount - 1) * spacingPx)
    val widthDp = with(density) { width.toDp() }

    Canvas(
        modifier = modifier
            .width(widthDp)
            .height(dotSize)
    ) {
        val radius = dotPx / 2
        val distance = dotPx + spacingPx

        // Draw inactive dots
        for (i in 0 until pagerState.pageCount) {
            drawCircle(
                color = inactiveColor,
                radius = radius,
                center = Offset(radius + i * distance, size.height / 2)
            )
        }

        // Draw active worm
        val absolutePage = pagerState.currentPage + pagerState.currentPageOffsetFraction
        val x = absolutePage.coerceIn(0f, (pagerState.pageCount - 1).toFloat())
        val i = kotlin.math.floor(x).toInt()
        val p = x - i

        val centerCurrent = radius + i * distance
        
        // Worm Head (Right side) moves first (0..0.5)
        // Worm Tail (Left side) moves second (0.5..1)
        
        val headOffset = if (p < 0.5f) p * 2 else 1f
        val tailOffset = if (p > 0.5f) (p - 0.5f) * 2 else 0f
        
        val left = centerCurrent - radius + (tailOffset * distance)
        val right = centerCurrent + radius + (headOffset * distance)
        
        drawRoundRect(
            color = activeColor,
            topLeft = Offset(left, 0f),
            size = Size(right - left, dotPx),
            cornerRadius = CornerRadius(radius, radius)
        )
    }
}
