package com.subot.core.ui.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import com.subot.core.ui.navigation.ListDetailScene.Companion.DETAIL_KEY
import com.subot.core.ui.navigation.ListDetailScene.Companion.LIST_KEY
import kotlin.reflect.KClass

// Window size class breakpoints (matching Material3 adaptive)
private val WIDTH_DP_MEDIUM_LOWER_BOUND = 600.dp
private val WIDTH_DP_EXPANDED_LOWER_BOUND = 840.dp
private val HEIGHT_DP_MEDIUM_LOWER_BOUND = 480.dp

class ListDetailScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    val listEntry: NavEntry<T>,
    val detailEntry: NavEntry<T>,
) : Scene<T> {
    override val entries: List<NavEntry<T>> = listOf(listEntry, detailEntry)
    override val content: @Composable (() -> Unit)
        get() = {
            // Use BoxWithConstraints to get actual dimensions during composition
            // This ensures we always have the correct dimensions on rotation
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val screenWidth = maxWidth

                // Adaptive weight ratios based on screen width:
                // - Expanded (840dp+): 0.4 list / 0.6 detail (landscape tablets, large screens)
                // - Medium (600-839dp): 0.5 list / 0.5 detail (portrait tablets, foldables - equal split)
                val isExpanded = screenWidth >= WIDTH_DP_EXPANDED_LOWER_BOUND
                val listWeight = if (isExpanded) 0.4f else 0.5f
                val detailWeight = if (isExpanded) 0.6f else 0.5f

                // Key on isExpanded to force Row recreation when orientation changes
                // This ensures the weight modifiers are reapplied with the new values
                key(isExpanded) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.weight(listWeight)) {
                            listEntry.Content()
                        }
                        Column(modifier = Modifier.weight(detailWeight)) {
                            AnimatedContent(
                                targetState = detailEntry,
                                contentKey = { entry -> entry.contentKey },
                                transitionSpec = {
                                    slideInHorizontally(
                                        initialOffsetX = { it }
                                    ) togetherWith
                                            slideOutHorizontally(targetOffsetX = { -it })
                                }
                            ) { entry ->
                                entry.Content()
                            }
                        }
                    }
                }
            }
        }

    companion object {
        internal const val LIST_KEY = "ListDetailScene_List"
        internal const val DETAIL_KEY = "ListDetailScene_Detail"

        fun listPane() = mapOf(LIST_KEY to true)

        fun detailPane() = mapOf(DETAIL_KEY to true)
    }
}

@Composable
fun <T : Any> rememberListDetailSceneStrategy(
    screenWidth: Dp,
    screenHeight: Dp
): ListDetailSceneStrategy<T> {
    // Create a fresh strategy each recomposition to ensure it always uses the current dimensions
    return ListDetailSceneStrategy(screenWidth, screenHeight)
}


/**
 * A [SceneStrategy] that returns a [ListDetailScene] if:
 *
 * - the window width is over 600dp
 * - A `Detail` entry is the last item in the back stack
 * - A `List` entry is in the back stack
 *
 * Notably, when the detail entry changes the scene's key does not change. This allows the scene,
 * rather than the NavDisplay, to handle animations when the detail entry changes.
 *
 * Note: The actual layout weights are determined in [ListDetailScene.content]
 * during composition using BoxWithConstraints for proper recomposition on configuration changes.
 */
class ListDetailSceneStrategy<T : Any>(
    private val screenWidth: Dp,
    private val screenHeight: Dp
) : SceneStrategy<T> {

    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
        // Only show list-detail layout on tablets/foldables (medium+ width AND medium+ height)
        // This prevents phones in landscape from using list-detail (wide but short)
        val isWidthMedium = screenWidth >= WIDTH_DP_MEDIUM_LOWER_BOUND
        val isHeightMedium = screenHeight >= HEIGHT_DP_MEDIUM_LOWER_BOUND
        if (!isWidthMedium || !isHeightMedium) {
            return null
        }

        val detailEntry =
            entries.lastOrNull()?.takeIf { it.metadata.containsKey(DETAIL_KEY) } ?: return null
        val listEntry = entries.findLast { it.metadata.containsKey(LIST_KEY) } ?: return null

        // Check if we're in expanded mode (landscape) or medium mode (portrait)
        val isExpanded = screenWidth >= WIDTH_DP_EXPANDED_LOWER_BOUND

        // Include isExpanded in the scene key to force scene recreation on orientation change
        // This ensures the layout weights are recalculated when rotating between portrait and landscape
        val sceneKey = "${listEntry.contentKey}_$isExpanded"


        return ListDetailScene(
            key = sceneKey,
            previousEntries = entries.dropLast(1),
            listEntry = listEntry,
            detailEntry = detailEntry
        )
    }
}

/**
 * Adds a detail route to the back stack, removing any existing detail routes of the same concrete type first.
 *
 * This is useful in list-detail scenarios where you want to replace the current detail view
 * rather than stacking multiple detail views.
 *
 * @param T The type of the detail route (must extend [NavKey])
 * @param detailRoute The detail route to add
 */
inline fun <reified T : NavKey> NavBackStack<NavKey>.addDetail(detailRoute: T) {
    // Remove any existing detail routes of the same concrete type before adding this detail route.
    // We use the concrete class of detailRoute to ensure we only remove routes of exactly the same type,
    // not routes that share a common base type.
    // In certain scenarios, such as when multiple detail panes can be shown at once, it may
    // be desirable to keep existing detail routes on the back stack.
    val detailClass = detailRoute::class
    removeAll { it::class == detailClass }
    add(detailRoute)
}

/**
 * Adds a detail route to the back stack, removing any existing detail routes of the specified class first.
 *
 * This variant allows specifying the class type explicitly, useful when the type cannot be inferred.
 *
 * @param detailRoute The detail route to add
 * @param detailClass The class of detail routes to remove before adding
 */
fun <T : NavKey> NavBackStack<NavKey>.addDetail(detailRoute: T, detailClass: KClass<out NavKey>) {
    // Remove any existing detail routes of the specified type before adding this detail route.
    removeAll { detailClass.isInstance(it) }
    add(detailRoute)
}

