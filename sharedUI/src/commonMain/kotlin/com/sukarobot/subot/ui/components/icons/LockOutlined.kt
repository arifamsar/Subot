package com.sukarobot.subot.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.LockOutlined: ImageVector
    get() {
        if (_LockOutlined != null) {
            return _LockOutlined!!
        }
        _LockOutlined = ImageVector.Builder(
            name = "LockOutlined",
            defaultWidth = 20.dp,
            defaultHeight = 22.dp,
            viewportWidth = 20f,
            viewportHeight = 22f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(10.5f, 11.75f)
                curveTo(10.5f, 11.336f, 10.164f, 11f, 9.75f, 11f)
                curveTo(9.336f, 11f, 9f, 11.336f, 9f, 11.75f)
                verticalLineTo(15.75f)
                curveTo(9f, 16.164f, 9.336f, 16.5f, 9.75f, 16.5f)
                curveTo(10.164f, 16.5f, 10.5f, 16.164f, 10.5f, 15.75f)
                verticalLineTo(11.75f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9.75f, 0f)
                curveTo(6.574f, 0f, 4f, 2.574f, 4f, 5.75f)
                verticalLineTo(6.273f)
                lineTo(3.973f, 6.281f)
                curveTo(2.223f, 6.85f, 0.85f, 8.223f, 0.281f, 9.973f)
                curveTo(-0f, 10.841f, -0f, 11.882f, 0f, 13.588f)
                verticalLineTo(13.912f)
                curveTo(-0f, 15.618f, -0f, 16.659f, 0.281f, 17.527f)
                curveTo(0.85f, 19.277f, 2.223f, 20.65f, 3.973f, 21.219f)
                curveTo(4.841f, 21.5f, 5.882f, 21.5f, 7.588f, 21.5f)
                horizontalLineTo(11.912f)
                curveTo(13.618f, 21.5f, 14.659f, 21.5f, 15.527f, 21.219f)
                curveTo(17.277f, 20.65f, 18.65f, 19.277f, 19.219f, 17.527f)
                curveTo(19.5f, 16.659f, 19.5f, 15.618f, 19.5f, 13.912f)
                verticalLineTo(13.588f)
                curveTo(19.5f, 11.882f, 19.5f, 10.841f, 19.219f, 9.973f)
                curveTo(18.65f, 8.223f, 17.277f, 6.85f, 15.527f, 6.281f)
                lineTo(15.5f, 6.273f)
                verticalLineTo(5.75f)
                curveTo(15.5f, 2.574f, 12.926f, 0f, 9.75f, 0f)
                close()
                moveTo(7.666f, 6f)
                curveTo(6.787f, 6f, 6.085f, 6f, 5.5f, 6.033f)
                verticalLineTo(5.75f)
                curveTo(5.5f, 3.403f, 7.403f, 1.5f, 9.75f, 1.5f)
                curveTo(12.097f, 1.5f, 14f, 3.403f, 14f, 5.75f)
                verticalLineTo(6.033f)
                curveTo(13.415f, 6f, 12.713f, 6f, 11.834f, 6f)
                horizontalLineTo(7.666f)
                close()
                moveTo(4.875f, 7.603f)
                curveTo(5.472f, 7.503f, 6.278f, 7.5f, 7.75f, 7.5f)
                horizontalLineTo(11.75f)
                curveTo(13.222f, 7.5f, 14.028f, 7.503f, 14.625f, 7.603f)
                curveTo(14.791f, 7.631f, 14.933f, 7.666f, 15.063f, 7.708f)
                curveTo(16.357f, 8.128f, 17.372f, 9.143f, 17.792f, 10.437f)
                curveTo(17.991f, 11.048f, 18f, 11.83f, 18f, 13.75f)
                curveTo(18f, 15.67f, 17.991f, 16.452f, 17.792f, 17.063f)
                curveTo(17.372f, 18.357f, 16.357f, 19.372f, 15.063f, 19.792f)
                curveTo(14.452f, 19.991f, 13.67f, 20f, 11.75f, 20f)
                horizontalLineTo(7.75f)
                curveTo(5.83f, 20f, 5.048f, 19.991f, 4.437f, 19.792f)
                curveTo(3.143f, 19.372f, 2.128f, 18.357f, 1.708f, 17.063f)
                curveTo(1.509f, 16.452f, 1.5f, 15.67f, 1.5f, 13.75f)
                curveTo(1.5f, 11.83f, 1.509f, 11.048f, 1.708f, 10.437f)
                curveTo(2.128f, 9.143f, 3.143f, 8.128f, 4.437f, 7.708f)
                curveTo(4.567f, 7.666f, 4.709f, 7.631f, 4.875f, 7.603f)
                close()
            }
        }.build()

        return _LockOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _LockOutlined: ImageVector? = null
