package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.HomeOutlined: ImageVector
    get() {
        if (_HomeOutlined != null) {
            return _HomeOutlined!!
        }
        _HomeOutlined = ImageVector.Builder(
            name = "HomeOutlined",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(10f, 17.07f)
                curveTo(9.586f, 17.07f, 9.25f, 17.405f, 9.25f, 17.82f)
                curveTo(9.25f, 18.234f, 9.586f, 18.57f, 10f, 18.57f)
                horizontalLineTo(14f)
                curveTo(14.414f, 18.57f, 14.75f, 18.234f, 14.75f, 17.82f)
                curveTo(14.75f, 17.405f, 14.414f, 17.07f, 14f, 17.07f)
                horizontalLineTo(10f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12.211f, 1.254f)
                curveTo(12.07f, 1.249f, 11.93f, 1.249f, 11.789f, 1.254f)
                curveTo(10.862f, 1.288f, 10.027f, 1.603f, 9.111f, 2.128f)
                curveTo(8.219f, 2.64f, 7.192f, 3.386f, 5.888f, 4.333f)
                lineTo(5.817f, 4.385f)
                curveTo(4.514f, 5.331f, 3.487f, 6.078f, 2.725f, 6.768f)
                curveTo(1.942f, 7.477f, 1.384f, 8.174f, 1.065f, 9.045f)
                curveTo(1.017f, 9.177f, 0.974f, 9.311f, 0.935f, 9.446f)
                curveTo(0.681f, 10.339f, 0.723f, 11.23f, 0.939f, 12.263f)
                curveTo(1.15f, 13.27f, 1.542f, 14.478f, 2.04f, 16.01f)
                lineTo(2.067f, 16.093f)
                curveTo(2.565f, 17.625f, 2.957f, 18.833f, 3.379f, 19.771f)
                curveTo(3.811f, 20.734f, 4.301f, 21.48f, 5.031f, 22.053f)
                curveTo(5.142f, 22.139f, 5.256f, 22.222f, 5.372f, 22.301f)
                curveTo(6.143f, 22.818f, 7.004f, 23.054f, 8.053f, 23.167f)
                curveTo(9.075f, 23.278f, 10.345f, 23.278f, 11.956f, 23.278f)
                horizontalLineTo(12.044f)
                curveTo(13.655f, 23.278f, 14.925f, 23.278f, 15.947f, 23.167f)
                curveTo(16.997f, 23.054f, 17.857f, 22.818f, 18.628f, 22.301f)
                curveTo(18.744f, 22.222f, 18.858f, 22.139f, 18.969f, 22.053f)
                curveTo(19.699f, 21.48f, 20.189f, 20.734f, 20.621f, 19.771f)
                curveTo(21.043f, 18.833f, 21.435f, 17.625f, 21.933f, 16.093f)
                lineTo(21.96f, 16.01f)
                curveTo(22.458f, 14.478f, 22.85f, 13.27f, 23.061f, 12.263f)
                curveTo(23.277f, 11.23f, 23.319f, 10.339f, 23.065f, 9.446f)
                curveTo(23.026f, 9.311f, 22.983f, 9.177f, 22.935f, 9.045f)
                curveTo(22.616f, 8.174f, 22.058f, 7.477f, 21.275f, 6.768f)
                curveTo(20.513f, 6.078f, 19.486f, 5.331f, 18.183f, 4.385f)
                lineTo(18.112f, 4.333f)
                curveTo(16.808f, 3.386f, 15.781f, 2.64f, 14.889f, 2.128f)
                curveTo(13.973f, 1.603f, 13.138f, 1.288f, 12.211f, 1.254f)
                close()
                moveTo(11.844f, 2.753f)
                curveTo(11.948f, 2.749f, 12.052f, 2.749f, 12.156f, 2.753f)
                curveTo(12.751f, 2.775f, 13.344f, 2.972f, 14.143f, 3.429f)
                curveTo(14.957f, 3.896f, 15.919f, 4.594f, 17.265f, 5.572f)
                curveTo(18.612f, 6.551f, 19.573f, 7.25f, 20.268f, 7.88f)
                curveTo(20.951f, 8.498f, 21.321f, 9.001f, 21.526f, 9.561f)
                curveTo(21.562f, 9.658f, 21.594f, 9.757f, 21.622f, 9.857f)
                curveTo(21.785f, 10.43f, 21.781f, 11.055f, 21.593f, 11.956f)
                curveTo(21.4f, 12.874f, 21.034f, 14.005f, 20.52f, 15.588f)
                curveTo(20.005f, 17.171f, 19.637f, 18.301f, 19.253f, 19.157f)
                curveTo(18.876f, 19.997f, 18.512f, 20.505f, 18.043f, 20.872f)
                curveTo(17.961f, 20.936f, 17.877f, 20.997f, 17.791f, 21.056f)
                curveTo(17.296f, 21.388f, 16.701f, 21.577f, 15.786f, 21.676f)
                curveTo(14.853f, 21.777f, 13.665f, 21.778f, 12f, 21.778f)
                curveTo(10.335f, 21.778f, 9.147f, 21.777f, 8.214f, 21.676f)
                curveTo(7.299f, 21.577f, 6.703f, 21.388f, 6.209f, 21.056f)
                curveTo(6.123f, 20.997f, 6.039f, 20.936f, 5.957f, 20.872f)
                curveTo(5.488f, 20.505f, 5.124f, 19.997f, 4.747f, 19.157f)
                curveTo(4.363f, 18.301f, 3.995f, 17.171f, 3.48f, 15.588f)
                curveTo(2.966f, 14.005f, 2.6f, 12.874f, 2.407f, 11.956f)
                curveTo(2.219f, 11.055f, 2.215f, 10.43f, 2.378f, 9.857f)
                curveTo(2.406f, 9.757f, 2.438f, 9.658f, 2.474f, 9.561f)
                curveTo(2.679f, 9.001f, 3.049f, 8.498f, 3.732f, 7.88f)
                curveTo(4.427f, 7.25f, 5.388f, 6.551f, 6.735f, 5.572f)
                curveTo(8.081f, 4.594f, 9.043f, 3.896f, 9.857f, 3.429f)
                curveTo(10.656f, 2.972f, 11.249f, 2.775f, 11.844f, 2.753f)
                close()
            }
        }.build()

        return _HomeOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _HomeOutlined: ImageVector? = null
