package com.sukarobot.subot.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.WalletOutlined: ImageVector
    get() {
        if (_WalletOutlined != null) {
            return _WalletOutlined!!
        }
        _WalletOutlined = ImageVector.Builder(
            name = "WalletOutlined",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(15.636f, 12.114f)
                curveTo(15.222f, 12.114f, 14.886f, 12.449f, 14.886f, 12.864f)
                curveTo(14.886f, 13.278f, 15.222f, 13.614f, 15.636f, 13.614f)
                horizontalLineTo(18.364f)
                curveTo(18.778f, 13.614f, 19.114f, 13.278f, 19.114f, 12.864f)
                curveTo(19.114f, 12.449f, 18.778f, 12.114f, 18.364f, 12.114f)
                horizontalLineTo(15.636f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(19.647f, 3.377f)
                curveTo(19.725f, 3.821f, 19.743f, 4.349f, 19.749f, 4.953f)
                curveTo(19.835f, 4.998f, 19.921f, 5.046f, 20.004f, 5.097f)
                curveTo(20.778f, 5.571f, 21.428f, 6.222f, 21.903f, 6.996f)
                curveTo(22.425f, 7.847f, 22.614f, 8.835f, 22.693f, 10.09f)
                curveTo(22.75f, 11.002f, 22.75f, 12.113f, 22.75f, 13.476f)
                verticalLineTo(13.505f)
                curveTo(22.75f, 14.287f, 22.75f, 14.983f, 22.74f, 15.603f)
                curveTo(22.708f, 17.531f, 22.583f, 18.894f, 21.903f, 20.004f)
                curveTo(21.428f, 20.778f, 20.778f, 21.429f, 20.004f, 21.903f)
                curveTo(19.265f, 22.355f, 18.427f, 22.557f, 17.404f, 22.654f)
                curveTo(16.4f, 22.75f, 15.143f, 22.75f, 13.539f, 22.75f)
                horizontalLineTo(10.46f)
                curveTo(8.857f, 22.75f, 7.6f, 22.75f, 6.596f, 22.654f)
                curveTo(5.572f, 22.557f, 4.734f, 22.355f, 3.995f, 21.903f)
                curveTo(3.222f, 21.429f, 2.571f, 20.778f, 2.097f, 20.004f)
                curveTo(1.644f, 19.266f, 1.443f, 18.427f, 1.345f, 17.404f)
                curveTo(1.25f, 16.4f, 1.25f, 15.143f, 1.25f, 13.539f)
                lineTo(1.25f, 13.014f)
                curveTo(1.237f, 12.519f, 1.241f, 11.967f, 1.245f, 11.371f)
                curveTo(1.247f, 11.057f, 1.25f, 10.731f, 1.25f, 10.396f)
                lineTo(1.25f, 10.352f)
                curveTo(1.25f, 8.64f, 1.25f, 7.296f, 1.361f, 6.23f)
                curveTo(1.474f, 5.142f, 1.709f, 4.255f, 2.239f, 3.494f)
                curveTo(2.559f, 3.034f, 2.948f, 2.629f, 3.391f, 2.293f)
                curveTo(4.13f, 1.734f, 4.994f, 1.485f, 6.047f, 1.366f)
                curveTo(7.075f, 1.25f, 8.368f, 1.25f, 10.006f, 1.25f)
                lineTo(15.474f, 1.25f)
                curveTo(16.273f, 1.25f, 16.95f, 1.25f, 17.489f, 1.326f)
                curveTo(18.066f, 1.406f, 18.595f, 1.586f, 19.018f, 2.027f)
                curveTo(19.385f, 2.41f, 19.559f, 2.872f, 19.647f, 3.377f)
                close()
                moveTo(15.422f, 2.75f)
                curveTo(16.287f, 2.75f, 16.858f, 2.752f, 17.281f, 2.811f)
                curveTo(17.68f, 2.867f, 17.834f, 2.96f, 17.935f, 3.066f)
                curveTo(18.029f, 3.163f, 18.113f, 3.31f, 18.17f, 3.635f)
                curveTo(18.208f, 3.852f, 18.228f, 4.118f, 18.239f, 4.458f)
                curveTo(17.826f, 4.383f, 17.381f, 4.336f, 16.896f, 4.306f)
                curveTo(15.986f, 4.25f, 14.88f, 4.25f, 13.523f, 4.25f)
                horizontalLineTo(10.46f)
                curveTo(8.857f, 4.25f, 7.6f, 4.25f, 6.596f, 4.345f)
                curveTo(5.572f, 4.443f, 4.734f, 4.645f, 3.995f, 5.097f)
                curveTo(3.597f, 5.341f, 3.232f, 5.632f, 2.906f, 5.962f)
                curveTo(3.016f, 5.235f, 3.195f, 4.745f, 3.47f, 4.351f)
                curveTo(3.701f, 4.019f, 3.981f, 3.728f, 4.297f, 3.489f)
                curveTo(4.735f, 3.157f, 5.297f, 2.96f, 6.215f, 2.857f)
                curveTo(7.149f, 2.751f, 8.358f, 2.75f, 10.053f, 2.75f)
                horizontalLineTo(15.422f)
                close()
                moveTo(4.779f, 6.376f)
                curveTo(5.243f, 6.092f, 5.824f, 5.926f, 6.738f, 5.839f)
                curveTo(7.663f, 5.751f, 8.849f, 5.75f, 10.5f, 5.75f)
                horizontalLineTo(13.5f)
                curveTo(14.884f, 5.75f, 15.945f, 5.75f, 16.803f, 5.803f)
                curveTo(17.661f, 5.856f, 18.261f, 5.959f, 18.734f, 6.139f)
                curveTo(18.911f, 6.206f, 19.071f, 6.285f, 19.221f, 6.376f)
                curveTo(19.792f, 6.727f, 20.273f, 7.208f, 20.624f, 7.779f)
                curveTo(20.87f, 8.182f, 21.027f, 8.671f, 21.122f, 9.386f)
                horizontalLineTo(15.636f)
                curveTo(13.716f, 9.386f, 12.159f, 10.943f, 12.159f, 12.864f)
                curveTo(12.159f, 14.784f, 13.716f, 16.341f, 15.636f, 16.341f)
                horizontalLineTo(21.219f)
                curveTo(21.161f, 17.801f, 21.002f, 18.603f, 20.624f, 19.221f)
                curveTo(20.273f, 19.792f, 19.792f, 20.273f, 19.221f, 20.624f)
                curveTo(18.757f, 20.908f, 18.176f, 21.074f, 17.262f, 21.161f)
                curveTo(16.337f, 21.249f, 15.151f, 21.25f, 13.5f, 21.25f)
                horizontalLineTo(10.5f)
                curveTo(8.849f, 21.25f, 7.663f, 21.249f, 6.738f, 21.161f)
                curveTo(5.824f, 21.074f, 5.243f, 20.908f, 4.779f, 20.624f)
                curveTo(4.207f, 20.273f, 3.727f, 19.792f, 3.376f, 19.221f)
                curveTo(3.092f, 18.757f, 2.925f, 18.176f, 2.839f, 17.262f)
                curveTo(2.751f, 16.337f, 2.75f, 15.151f, 2.75f, 13.5f)
                curveTo(2.75f, 11.849f, 2.751f, 10.663f, 2.839f, 9.738f)
                curveTo(2.925f, 8.824f, 3.092f, 8.243f, 3.376f, 7.779f)
                curveTo(3.727f, 7.208f, 4.207f, 6.727f, 4.779f, 6.376f)
                close()
                moveTo(13.659f, 12.864f)
                curveTo(13.659f, 11.772f, 14.544f, 10.886f, 15.636f, 10.886f)
                horizontalLineTo(21.227f)
                curveTo(21.25f, 11.61f, 21.25f, 12.464f, 21.25f, 13.5f)
                curveTo(21.25f, 13.984f, 21.25f, 14.429f, 21.247f, 14.841f)
                horizontalLineTo(15.636f)
                curveTo(14.544f, 14.841f, 13.659f, 13.956f, 13.659f, 12.864f)
                close()
            }
        }.build()

        return _WalletOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _WalletOutlined: ImageVector? = null
