package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.HomeFilled: ImageVector
    get() {
        if (_HomeFilled != null) {
            return _HomeFilled!!
        }
        _HomeFilled = ImageVector.Builder(
            name = "HomeFilled",
            defaultWidth = 21.dp,
            defaultHeight = 21.dp,
            viewportWidth = 21f,
            viewportHeight = 21f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(4.783f, 2.966f)
                curveTo(2.117f, 4.903f, 0.783f, 5.872f, 0.259f, 7.303f)
                curveTo(0.217f, 7.418f, 0.18f, 7.534f, 0.146f, 7.652f)
                curveTo(-0.271f, 9.117f, 0.238f, 10.685f, 1.257f, 13.82f)
                curveTo(2.275f, 16.955f, 2.785f, 18.522f, 3.984f, 19.462f)
                curveTo(4.08f, 19.538f, 4.179f, 19.61f, 4.28f, 19.678f)
                curveTo(5.545f, 20.528f, 7.193f, 20.528f, 10.49f, 20.528f)
                curveTo(13.786f, 20.528f, 15.434f, 20.528f, 16.699f, 19.678f)
                curveTo(16.801f, 19.61f, 16.899f, 19.538f, 16.996f, 19.462f)
                curveTo(18.195f, 18.522f, 18.704f, 16.955f, 19.723f, 13.82f)
                curveTo(20.741f, 10.685f, 21.251f, 9.117f, 20.833f, 7.652f)
                curveTo(20.8f, 7.534f, 20.762f, 7.418f, 20.72f, 7.303f)
                curveTo(20.196f, 5.872f, 18.863f, 4.903f, 16.196f, 2.966f)
                curveTo(13.529f, 1.028f, 12.196f, 0.059f, 10.673f, 0.003f)
                curveTo(10.551f, -0.001f, 10.429f, -0.001f, 10.307f, 0.003f)
                curveTo(8.784f, 0.059f, 7.45f, 1.028f, 4.783f, 2.966f)
                close()
                moveTo(8.49f, 15.07f)
                curveTo(8.076f, 15.07f, 7.74f, 15.405f, 7.74f, 15.82f)
                curveTo(7.74f, 16.234f, 8.076f, 16.57f, 8.49f, 16.57f)
                horizontalLineTo(12.49f)
                curveTo(12.904f, 16.57f, 13.24f, 16.234f, 13.24f, 15.82f)
                curveTo(13.24f, 15.405f, 12.904f, 15.07f, 12.49f, 15.07f)
                horizontalLineTo(8.49f)
                close()
            }
        }.build()

        return _HomeFilled!!
    }

@Suppress("ObjectPropertyName")
private var _HomeFilled: ImageVector? = null
