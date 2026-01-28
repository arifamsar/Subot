package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.Global: ImageVector
    get() {
        if (_Global != null) {
            return _Global!!
        }
        _Global = ImageVector.Builder(
            name = "Global",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 2.25f)
                curveTo(6.615f, 2.25f, 2.25f, 6.615f, 2.25f, 12f)
                curveTo(2.25f, 17.385f, 6.615f, 21.75f, 12f, 21.75f)
                curveTo(17.385f, 21.75f, 21.75f, 17.385f, 21.75f, 12f)
                curveTo(21.75f, 6.615f, 17.385f, 2.25f, 12f, 2.25f)
                close()
                moveTo(9.894f, 3.902f)
                curveTo(9.237f, 5.11f, 8.728f, 6.614f, 8.416f, 8.25f)
                horizontalLineTo(5.072f)
                curveTo(6.058f, 6.229f, 7.788f, 4.655f, 9.894f, 3.902f)
                close()
                moveTo(4.592f, 9.75f)
                horizontalLineTo(8.263f)
                curveTo(8.189f, 10.489f, 8.15f, 11.239f, 8.15f, 12f)
                curveTo(8.15f, 12.761f, 8.189f, 13.511f, 8.263f, 14.25f)
                horizontalLineTo(4.592f)
                curveTo(4.366f, 13.532f, 4.25f, 12.779f, 4.25f, 12f)
                curveTo(4.25f, 11.221f, 4.366f, 10.468f, 4.592f, 9.75f)
                close()
                moveTo(5.072f, 15.75f)
                horizontalLineTo(8.416f)
                curveTo(8.728f, 17.386f, 9.237f, 18.89f, 9.894f, 20.098f)
                curveTo(7.788f, 19.345f, 6.058f, 17.771f, 5.072f, 15.75f)
                close()
                moveTo(14.106f, 20.098f)
                curveTo(14.763f, 18.89f, 15.272f, 17.386f, 15.584f, 15.75f)
                horizontalLineTo(18.928f)
                curveTo(17.942f, 17.771f, 16.212f, 19.345f, 14.106f, 20.098f)
                close()
                moveTo(19.408f, 14.25f)
                horizontalLineTo(15.737f)
                curveTo(15.811f, 13.511f, 15.85f, 12.761f, 15.85f, 12f)
                curveTo(15.85f, 11.239f, 15.811f, 10.489f, 15.737f, 9.75f)
                horizontalLineTo(19.408f)
                curveTo(19.634f, 10.468f, 19.75f, 11.221f, 19.75f, 12f)
                curveTo(19.75f, 12.779f, 19.634f, 13.532f, 19.408f, 14.25f)
                close()
                moveTo(18.928f, 8.25f)
                horizontalLineTo(15.584f)
                curveTo(15.272f, 6.614f, 14.763f, 5.11f, 14.106f, 3.902f)
                curveTo(16.212f, 4.655f, 17.942f, 6.229f, 18.928f, 8.25f)
                close()
                moveTo(13.978f, 8.25f)
                horizontalLineTo(10.022f)
                curveTo(10.359f, 6.419f, 10.944f, 4.783f, 11.688f, 3.543f)
                curveTo(11.787f, 3.386f, 11.891f, 3.238f, 12f, 3.1f)
                curveTo(12.109f, 3.238f, 12.213f, 3.386f, 12.312f, 3.543f)
                curveTo(13.056f, 4.783f, 13.641f, 6.419f, 13.978f, 8.25f)
                close()
                moveTo(14.15f, 12f)
                curveTo(14.15f, 11.213f, 14.108f, 10.462f, 14.031f, 9.75f)
                horizontalLineTo(9.969f)
                curveTo(9.892f, 10.462f, 9.85f, 11.213f, 9.85f, 12f)
                curveTo(9.85f, 12.787f, 9.892f, 13.538f, 9.969f, 14.25f)
                horizontalLineTo(14.031f)
                curveTo(14.108f, 13.538f, 14.15f, 12.787f, 14.15f, 12f)
                close()
                moveTo(13.978f, 15.75f)
                horizontalLineTo(10.022f)
                curveTo(10.359f, 17.581f, 10.944f, 19.217f, 11.688f, 20.457f)
                curveTo(11.787f, 20.614f, 11.891f, 20.762f, 12f, 20.9f)
                curveTo(12.109f, 20.762f, 12.213f, 20.614f, 12.312f, 20.457f)
                curveTo(13.056f, 19.217f, 13.641f, 17.581f, 13.978f, 15.75f)
                close()
            }
        }.build()

        return _Global!!
    }

@Suppress("ObjectPropertyName")
private var _Global: ImageVector? = null
