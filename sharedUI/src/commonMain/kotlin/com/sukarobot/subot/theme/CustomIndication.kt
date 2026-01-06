package com.sukarobot.subot.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Custom indication with scale animation effect
 * Provides a subtle scale animation on press instead of ripple effect
 */
object ScaleIndication : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return ScaleIndicationNode(interactionSource)
    }
    
    override fun equals(other: Any?): Boolean = other === this
    override fun hashCode(): Int = -1
}

private class ScaleIndicationNode(
    private val interactionSource: InteractionSource
) : Modifier.Node(), DrawModifierNode {
    
    private val animatedScale = Animatable(1f)
    
    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        animatedScale.animateTo(
                            targetValue = 0.95f,
                            animationSpec = tween(100)
                        )
                    }
                    is PressInteraction.Release,
                    is PressInteraction.Cancel -> {
                        animatedScale.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(100)
                        )
                    }
                }
            }
        }
    }
    
    override fun ContentDrawScope.draw() {
        scale(animatedScale.value) {
            this@draw.drawContent()
        }
    }
}

/**
 * No indication implementation - completely removes interaction feedback
 */
object NoIndication : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return NoIndicationNode()
    }
    
    override fun equals(other: Any?): Boolean = other === this
    override fun hashCode(): Int = 0
}

private class NoIndicationNode : Modifier.Node(), DrawModifierNode {
    override fun ContentDrawScope.draw() {
        drawContent()
    }
}

/**
 * Custom indication for NavigationBar items
 * Provides a subtle background highlight with scale effect on press
 */
class NavigationBarIndication(
    private val color: Color
) : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return NavigationBarIndicationNode(interactionSource, color)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NavigationBarIndication) return false
        return color == other.color
    }

    override fun hashCode(): Int = color.hashCode()
}

private class NavigationBarIndicationNode(
    private val interactionSource: InteractionSource,
    private val color: Color
) : Modifier.Node(), DrawModifierNode {

    private val animatedAlpha = Animatable(0f)
    private val animatedScale = Animatable(1f)

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        launch {
                            animatedAlpha.animateTo(
                                targetValue = 0.12f,
                                animationSpec = tween(100)
                            )
                        }
                        launch {
                            animatedScale.animateTo(
                                targetValue = 0.96f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessMedium
                                )
                            )
                        }
                    }
                    is PressInteraction.Release,
                    is PressInteraction.Cancel -> {
                        launch {
                            animatedAlpha.animateTo(
                                targetValue = 0f,
                                animationSpec = tween(150)
                            )
                        }
                        launch {
                            animatedScale.animateTo(
                                targetValue = 1f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessMedium
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    override fun ContentDrawScope.draw() {
        // Draw background highlight
        if (animatedAlpha.value > 0f) {
            val padding = 8.dp.toPx()
            val indicatorSize = Size(
                width = size.width - padding * 2,
                height = size.height - padding * 2
            )
            val indicatorOffset = Offset(
                x = padding,
                y = padding
            )

            drawRoundRect(
                color = color.copy(alpha = animatedAlpha.value),
                topLeft = indicatorOffset,
                size = indicatorSize,
                cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
            )
        }

        // Draw content with scale
        scale(animatedScale.value) {
            this@draw.drawContent()
        }
    }
}

