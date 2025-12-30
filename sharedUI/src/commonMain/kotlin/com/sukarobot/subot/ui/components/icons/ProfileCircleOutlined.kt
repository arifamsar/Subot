package com.sukarobot.subot.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.ProfileCircleOutlined: ImageVector
    get() {
        if (_ProfileCircleOutlined != null) {
            return _ProfileCircleOutlined!!
        }
        _ProfileCircleOutlined = ImageVector.Builder(
            name = "ProfileCircleOutlined",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 4.25f)
                curveTo(9.377f, 4.25f, 7.25f, 6.377f, 7.25f, 9f)
                curveTo(7.25f, 11.623f, 9.377f, 13.75f, 12f, 13.75f)
                curveTo(14.623f, 13.75f, 16.75f, 11.623f, 16.75f, 9f)
                curveTo(16.75f, 6.377f, 14.623f, 4.25f, 12f, 4.25f)
                close()
                moveTo(8.75f, 9f)
                curveTo(8.75f, 7.205f, 10.205f, 5.75f, 12f, 5.75f)
                curveTo(13.795f, 5.75f, 15.25f, 7.205f, 15.25f, 9f)
                curveTo(15.25f, 10.795f, 13.795f, 12.25f, 12f, 12.25f)
                curveTo(10.205f, 12.25f, 8.75f, 10.795f, 8.75f, 9f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 1.25f)
                curveTo(6.063f, 1.25f, 1.25f, 6.063f, 1.25f, 12f)
                curveTo(1.25f, 15.049f, 2.52f, 17.802f, 4.558f, 19.757f)
                curveTo(6.488f, 21.61f, 9.112f, 22.75f, 12f, 22.75f)
                curveTo(14.888f, 22.75f, 17.512f, 21.61f, 19.442f, 19.757f)
                curveTo(21.48f, 17.802f, 22.75f, 15.049f, 22.75f, 12f)
                curveTo(22.75f, 6.063f, 17.937f, 1.25f, 12f, 1.25f)
                close()
                moveTo(2.75f, 12f)
                curveTo(2.75f, 6.891f, 6.891f, 2.75f, 12f, 2.75f)
                curveTo(17.109f, 2.75f, 21.25f, 6.891f, 21.25f, 12f)
                curveTo(21.25f, 14.195f, 20.486f, 16.21f, 19.209f, 17.797f)
                curveTo(18.415f, 16.283f, 16.829f, 15.25f, 15f, 15.25f)
                horizontalLineTo(9f)
                curveTo(7.171f, 15.25f, 5.585f, 16.283f, 4.791f, 17.797f)
                curveTo(3.514f, 16.21f, 2.75f, 14.195f, 2.75f, 12f)
                close()
                moveTo(15f, 16.75f)
                curveTo(16.434f, 16.75f, 17.652f, 17.679f, 18.083f, 18.969f)
                curveTo(16.456f, 20.39f, 14.329f, 21.25f, 12f, 21.25f)
                curveTo(9.671f, 21.25f, 7.544f, 20.39f, 5.917f, 18.969f)
                curveTo(6.348f, 17.679f, 7.566f, 16.75f, 9f, 16.75f)
                horizontalLineTo(15f)
                close()
            }
        }.build()

        return _ProfileCircleOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _ProfileCircleOutlined: ImageVector? = null
