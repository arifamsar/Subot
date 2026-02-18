package com.subot.core.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.subot.core.ui.components.icons.ArrowLeft
import com.subot.core.ui.components.icons.Hicon
import com.subot.core.ui.isLiquidEnabled
import io.github.fletchmckee.liquid.liquid
import io.github.fletchmckee.liquid.rememberLiquidState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector = Hicon.ArrowLeft,
    onNavigationClick: (() -> Unit)? = null,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    actions: @Composable () -> Unit = {},
) {
    val liquidTopBarState = rememberLiquidState()
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier.then(
            if (isLiquidEnabled()) {
                Modifier.liquid(liquidState = liquidTopBarState) {
                    this.shape = CutCornerShape(0.dp)
                    this.frost = 8.dp
                    this.curve = .4f
                    this.refraction = .1f
                    this.dispersion = .2f
                }
            } else Modifier
        ),
        navigationIcon = {
            if (onNavigationClick != null) {
                IconButton(
                    shapes = IconButtonDefaults.shapes(),
                    onClick = onNavigationClick
                ) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = "Back"
                    )
                }
            }
        },
        windowInsets = windowInsets,
        actions = { actions() },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        )
    )
}