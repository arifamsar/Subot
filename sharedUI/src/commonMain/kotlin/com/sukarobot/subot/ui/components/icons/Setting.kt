package com.sukarobot.subot.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.Setting: ImageVector
    get() {
        if (_Setting != null) {
            return _Setting!!
        }
        _Setting = ImageVector.Builder(
            name = "Setting",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 8.25f)
                curveTo(9.929f, 8.25f, 8.25f, 9.929f, 8.25f, 12f)
                curveTo(8.25f, 14.071f, 9.929f, 15.75f, 12f, 15.75f)
                curveTo(14.071f, 15.75f, 15.75f, 14.071f, 15.75f, 12f)
                curveTo(15.75f, 9.929f, 14.071f, 8.25f, 12f, 8.25f)
                close()
                moveTo(9.75f, 12f)
                curveTo(9.75f, 10.757f, 10.757f, 9.75f, 12f, 9.75f)
                curveTo(13.243f, 9.75f, 14.25f, 10.757f, 14.25f, 12f)
                curveTo(14.25f, 13.243f, 13.243f, 14.25f, 12f, 14.25f)
                curveTo(10.757f, 14.25f, 9.75f, 13.243f, 9.75f, 12f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(15.415f, 3.232f)
                curveTo(14.434f, -0.203f, 9.566f, -0.203f, 8.585f, 3.232f)
                curveTo(8.281f, 4.297f, 7.188f, 4.928f, 6.114f, 4.659f)
                curveTo(2.649f, 3.79f, 0.215f, 8.007f, 2.7f, 10.573f)
                curveTo(3.469f, 11.369f, 3.469f, 12.631f, 2.7f, 13.427f)
                curveTo(0.215f, 15.993f, 2.649f, 20.21f, 6.114f, 19.341f)
                curveTo(7.188f, 19.072f, 8.281f, 19.703f, 8.585f, 20.768f)
                curveTo(9.566f, 24.203f, 14.434f, 24.203f, 15.415f, 20.768f)
                curveTo(15.718f, 19.703f, 16.812f, 19.072f, 17.886f, 19.341f)
                curveTo(21.351f, 20.21f, 23.785f, 15.993f, 21.3f, 13.427f)
                curveTo(20.531f, 12.631f, 20.531f, 11.369f, 21.3f, 10.573f)
                curveTo(23.785f, 8.007f, 21.351f, 3.79f, 17.886f, 4.659f)
                curveTo(16.812f, 4.928f, 15.718f, 4.297f, 15.415f, 3.232f)
                close()
                moveTo(10.028f, 3.644f)
                curveTo(10.594f, 1.66f, 13.406f, 1.66f, 13.972f, 3.644f)
                curveTo(14.498f, 5.487f, 16.392f, 6.58f, 18.25f, 6.114f)
                curveTo(20.252f, 5.612f, 21.658f, 8.048f, 20.223f, 9.53f)
                curveTo(18.89f, 10.907f, 18.89f, 13.093f, 20.223f, 14.47f)
                curveTo(21.658f, 15.953f, 20.252f, 18.388f, 18.25f, 17.886f)
                curveTo(16.392f, 17.42f, 14.498f, 18.513f, 13.972f, 20.356f)
                curveTo(13.406f, 22.34f, 10.594f, 22.34f, 10.028f, 20.356f)
                curveTo(9.502f, 18.513f, 7.608f, 17.42f, 5.75f, 17.886f)
                curveTo(3.748f, 18.388f, 2.342f, 15.953f, 3.777f, 14.47f)
                curveTo(5.11f, 13.093f, 5.11f, 10.907f, 3.777f, 9.53f)
                curveTo(2.342f, 8.048f, 3.748f, 5.612f, 5.75f, 6.114f)
                curveTo(7.608f, 6.58f, 9.502f, 5.487f, 10.028f, 3.644f)
                close()
            }
        }.build()

        return _Setting!!
    }

@Suppress("ObjectPropertyName")
private var _Setting: ImageVector? = null
