package com.sukarobot.subot.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.CalenderFilled: ImageVector
    get() {
        if (_CalenderFilled != null) {
            return _CalenderFilled!!
        }
        _CalenderFilled = ImageVector.Builder(
            name = "CalenderFilled",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(5.061f, 4.955f)
                curveTo(5.695f, 4.495f, 6.459f, 4.256f, 7.5f, 4.133f)
                verticalLineTo(5f)
                curveTo(7.5f, 5.828f, 8.172f, 6.5f, 9f, 6.5f)
                curveTo(9.828f, 6.5f, 10.5f, 5.828f, 10.5f, 5f)
                verticalLineTo(4.003f)
                curveTo(10.964f, 4f, 11.463f, 4f, 12f, 4f)
                curveTo(12.537f, 4f, 13.036f, 4f, 13.5f, 4.003f)
                verticalLineTo(5f)
                curveTo(13.5f, 5.828f, 14.172f, 6.5f, 15f, 6.5f)
                curveTo(15.828f, 6.5f, 16.5f, 5.828f, 16.5f, 5f)
                verticalLineTo(4.133f)
                curveTo(17.541f, 4.256f, 18.305f, 4.495f, 18.939f, 4.955f)
                curveTo(19.363f, 5.263f, 19.737f, 5.637f, 20.045f, 6.061f)
                curveTo(20.609f, 6.838f, 20.84f, 7.81f, 20.935f, 9.25f)
                horizontalLineTo(3.065f)
                curveTo(3.16f, 7.81f, 3.391f, 6.838f, 3.955f, 6.061f)
                curveTo(4.263f, 5.637f, 4.637f, 5.263f, 5.061f, 4.955f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(3.011f, 10.75f)
                curveTo(3f, 11.416f, 3f, 12.161f, 3f, 13f)
                curveTo(3f, 16.75f, 3f, 18.625f, 3.955f, 19.939f)
                curveTo(4.263f, 20.363f, 4.637f, 20.737f, 5.061f, 21.045f)
                curveTo(6.375f, 22f, 8.25f, 22f, 12f, 22f)
                curveTo(12.839f, 22f, 13.584f, 22f, 14.25f, 21.989f)
                lineTo(14.25f, 21.919f)
                curveTo(14.25f, 21.067f, 14.25f, 20.548f, 14.321f, 20.101f)
                curveTo(14.71f, 17.64f, 16.64f, 15.71f, 19.101f, 15.321f)
                curveTo(19.548f, 15.25f, 20.067f, 15.25f, 20.919f, 15.25f)
                lineTo(20.989f, 15.25f)
                curveTo(21f, 14.584f, 21f, 13.839f, 21f, 13f)
                curveTo(21f, 12.161f, 21f, 11.416f, 20.989f, 10.75f)
                lineTo(20.971f, 10.75f)
                horizontalLineTo(3.029f)
                lineTo(3.011f, 10.75f)
                close()
                moveTo(9f, 13.25f)
                curveTo(8.586f, 13.25f, 8.25f, 13.586f, 8.25f, 14f)
                curveTo(8.25f, 14.414f, 8.586f, 14.75f, 9f, 14.75f)
                horizontalLineTo(15f)
                curveTo(15.414f, 14.75f, 15.75f, 14.414f, 15.75f, 14f)
                curveTo(15.75f, 13.586f, 15.414f, 13.25f, 15f, 13.25f)
                horizontalLineTo(9f)
                close()
            }
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(18.939f, 21.045f)
                curveTo(18.162f, 21.609f, 17.19f, 21.84f, 15.75f, 21.935f)
                curveTo(15.75f, 21.024f, 15.753f, 20.643f, 15.802f, 20.335f)
                curveTo(16.09f, 18.517f, 17.517f, 17.09f, 19.335f, 16.802f)
                curveTo(19.643f, 16.753f, 20.024f, 16.75f, 20.935f, 16.75f)
                curveTo(20.84f, 18.19f, 20.609f, 19.162f, 20.045f, 19.939f)
                curveTo(19.737f, 20.363f, 19.363f, 20.737f, 18.939f, 21.045f)
                close()
            }
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(9.75f, 2f)
                curveTo(9.75f, 1.586f, 9.414f, 1.25f, 9f, 1.25f)
                curveTo(8.586f, 1.25f, 8.25f, 1.586f, 8.25f, 2f)
                verticalLineTo(5f)
                curveTo(8.25f, 5.414f, 8.586f, 5.75f, 9f, 5.75f)
                curveTo(9.414f, 5.75f, 9.75f, 5.414f, 9.75f, 5f)
                verticalLineTo(2f)
                close()
            }
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(15.75f, 2f)
                curveTo(15.75f, 1.586f, 15.414f, 1.25f, 15f, 1.25f)
                curveTo(14.586f, 1.25f, 14.25f, 1.586f, 14.25f, 2f)
                verticalLineTo(5f)
                curveTo(14.25f, 5.414f, 14.586f, 5.75f, 15f, 5.75f)
                curveTo(15.414f, 5.75f, 15.75f, 5.414f, 15.75f, 5f)
                verticalLineTo(2f)
                close()
            }
        }.build()

        return _CalenderFilled!!
    }

@Suppress("ObjectPropertyName")
private var _CalenderFilled: ImageVector? = null
