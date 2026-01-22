package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.SunOutlined: ImageVector
    get() {
        if (_SunOutlined != null) {
            return _SunOutlined!!
        }
        _SunOutlined = ImageVector.Builder(
            name = "SunOutlined",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(0f, 0f)
                    horizontalLineToRelative(24f)
                    verticalLineToRelative(24f)
                    horizontalLineToRelative(-24f)
                    close()
                }
            ) {
                path(fill = SolidColor(Color(0xFF040000))) {
                    moveTo(12.75f, 1f)
                    curveTo(12.75f, 0.586f, 12.414f, 0.25f, 12f, 0.25f)
                    curveTo(11.586f, 0.25f, 11.25f, 0.586f, 11.25f, 1f)
                    verticalLineTo(3f)
                    curveTo(11.25f, 3.414f, 11.586f, 3.75f, 12f, 3.75f)
                    curveTo(12.414f, 3.75f, 12.75f, 3.414f, 12.75f, 3f)
                    verticalLineTo(1f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF040000))) {
                    moveTo(4.752f, 3.691f)
                    curveTo(4.459f, 3.399f, 3.984f, 3.399f, 3.691f, 3.691f)
                    curveTo(3.399f, 3.984f, 3.399f, 4.459f, 3.691f, 4.752f)
                    lineTo(5.106f, 6.166f)
                    curveTo(5.399f, 6.459f, 5.873f, 6.459f, 6.166f, 6.166f)
                    curveTo(6.459f, 5.873f, 6.459f, 5.399f, 6.166f, 5.106f)
                    lineTo(4.752f, 3.691f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF040000))) {
                    moveTo(20.309f, 4.752f)
                    curveTo(20.601f, 4.459f, 20.601f, 3.984f, 20.309f, 3.691f)
                    curveTo(20.016f, 3.399f, 19.541f, 3.399f, 19.248f, 3.691f)
                    lineTo(17.834f, 5.106f)
                    curveTo(17.541f, 5.399f, 17.541f, 5.873f, 17.834f, 6.166f)
                    curveTo(18.126f, 6.459f, 18.601f, 6.459f, 18.894f, 6.166f)
                    lineTo(20.309f, 4.752f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF040000)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(12f, 5.25f)
                    curveTo(8.272f, 5.25f, 5.25f, 8.272f, 5.25f, 12f)
                    curveTo(5.25f, 15.728f, 8.272f, 18.75f, 12f, 18.75f)
                    curveTo(15.728f, 18.75f, 18.75f, 15.728f, 18.75f, 12f)
                    curveTo(18.75f, 8.272f, 15.728f, 5.25f, 12f, 5.25f)
                    close()
                    moveTo(6.75f, 12f)
                    curveTo(6.75f, 9.101f, 9.101f, 6.75f, 12f, 6.75f)
                    curveTo(14.899f, 6.75f, 17.25f, 9.101f, 17.25f, 12f)
                    curveTo(17.25f, 14.899f, 14.899f, 17.25f, 12f, 17.25f)
                    curveTo(9.101f, 17.25f, 6.75f, 14.899f, 6.75f, 12f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF040000))) {
                    moveTo(1f, 11.25f)
                    curveTo(0.586f, 11.25f, 0.25f, 11.586f, 0.25f, 12f)
                    curveTo(0.25f, 12.414f, 0.586f, 12.75f, 1f, 12.75f)
                    horizontalLineTo(3f)
                    curveTo(3.414f, 12.75f, 3.75f, 12.414f, 3.75f, 12f)
                    curveTo(3.75f, 11.586f, 3.414f, 11.25f, 3f, 11.25f)
                    horizontalLineTo(1f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF040000))) {
                    moveTo(21f, 11.25f)
                    curveTo(20.586f, 11.25f, 20.25f, 11.586f, 20.25f, 12f)
                    curveTo(20.25f, 12.414f, 20.586f, 12.75f, 21f, 12.75f)
                    horizontalLineTo(23f)
                    curveTo(23.414f, 12.75f, 23.75f, 12.414f, 23.75f, 12f)
                    curveTo(23.75f, 11.586f, 23.414f, 11.25f, 23f, 11.25f)
                    horizontalLineTo(21f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF040000))) {
                    moveTo(6.166f, 18.894f)
                    curveTo(6.459f, 18.601f, 6.459f, 18.126f, 6.166f, 17.834f)
                    curveTo(5.873f, 17.541f, 5.399f, 17.541f, 5.106f, 17.834f)
                    lineTo(3.691f, 19.248f)
                    curveTo(3.399f, 19.541f, 3.399f, 20.016f, 3.691f, 20.309f)
                    curveTo(3.984f, 20.601f, 4.459f, 20.601f, 4.752f, 20.309f)
                    lineTo(6.166f, 18.894f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF040000))) {
                    moveTo(18.894f, 17.834f)
                    curveTo(18.601f, 17.541f, 18.126f, 17.541f, 17.834f, 17.834f)
                    curveTo(17.541f, 18.126f, 17.541f, 18.601f, 17.834f, 18.894f)
                    lineTo(19.248f, 20.309f)
                    curveTo(19.541f, 20.601f, 20.016f, 20.601f, 20.309f, 20.309f)
                    curveTo(20.601f, 20.016f, 20.601f, 19.541f, 20.309f, 19.248f)
                    lineTo(18.894f, 17.834f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF040000))) {
                    moveTo(12.75f, 21f)
                    curveTo(12.75f, 20.586f, 12.414f, 20.25f, 12f, 20.25f)
                    curveTo(11.586f, 20.25f, 11.25f, 20.586f, 11.25f, 21f)
                    verticalLineTo(23f)
                    curveTo(11.25f, 23.414f, 11.586f, 23.75f, 12f, 23.75f)
                    curveTo(12.414f, 23.75f, 12.75f, 23.414f, 12.75f, 23f)
                    verticalLineTo(21f)
                    close()
                }
            }
        }.build()

        return _SunOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _SunOutlined: ImageVector? = null
