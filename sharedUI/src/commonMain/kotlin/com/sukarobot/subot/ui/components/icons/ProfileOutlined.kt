package com.sukarobot.subot.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.ProfileOutlined: ImageVector
    get() {
        if (_ProfileOutlined != null) {
            return _ProfileOutlined!!
        }
        _ProfileOutlined = ImageVector.Builder(
            name = "ProfileOutlined",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 1.25f)
                curveTo(9.377f, 1.25f, 7.25f, 3.377f, 7.25f, 6f)
                curveTo(7.25f, 8.623f, 9.377f, 10.75f, 12f, 10.75f)
                curveTo(14.623f, 10.75f, 16.75f, 8.623f, 16.75f, 6f)
                curveTo(16.75f, 3.377f, 14.623f, 1.25f, 12f, 1.25f)
                close()
                moveTo(8.75f, 6f)
                curveTo(8.75f, 4.205f, 10.205f, 2.75f, 12f, 2.75f)
                curveTo(13.795f, 2.75f, 15.25f, 4.205f, 15.25f, 6f)
                curveTo(15.25f, 7.795f, 13.795f, 9.25f, 12f, 9.25f)
                curveTo(10.205f, 9.25f, 8.75f, 7.795f, 8.75f, 6f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9f, 12.25f)
                curveTo(6.377f, 12.25f, 4.25f, 14.377f, 4.25f, 17f)
                curveTo(4.25f, 19.623f, 6.377f, 21.75f, 9f, 21.75f)
                horizontalLineTo(15f)
                curveTo(17.623f, 21.75f, 19.75f, 19.623f, 19.75f, 17f)
                curveTo(19.75f, 14.377f, 17.623f, 12.25f, 15f, 12.25f)
                horizontalLineTo(9f)
                close()
                moveTo(5.75f, 17f)
                curveTo(5.75f, 15.205f, 7.205f, 13.75f, 9f, 13.75f)
                horizontalLineTo(15f)
                curveTo(16.795f, 13.75f, 18.25f, 15.205f, 18.25f, 17f)
                curveTo(18.25f, 18.795f, 16.795f, 20.25f, 15f, 20.25f)
                horizontalLineTo(9f)
                curveTo(7.205f, 20.25f, 5.75f, 18.795f, 5.75f, 17f)
                close()
            }
        }.build()

        return _ProfileOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _ProfileOutlined: ImageVector? = null
