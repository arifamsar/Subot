package com.sukarobot.subot.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.Logout: ImageVector
    get() {
        if (_Logout != null) {
            return _Logout!!
        }
        _Logout = ImageVector.Builder(
            name = "Logout",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(8.919f, 2.25f)
                curveTo(8.067f, 2.25f, 7.548f, 2.25f, 7.101f, 2.321f)
                curveTo(4.64f, 2.71f, 2.71f, 4.64f, 2.321f, 7.101f)
                curveTo(2.25f, 7.548f, 2.25f, 8.067f, 2.25f, 8.919f)
                lineTo(2.25f, 15.081f)
                curveTo(2.25f, 15.933f, 2.25f, 16.452f, 2.321f, 16.899f)
                curveTo(2.71f, 19.36f, 4.64f, 21.289f, 7.101f, 21.679f)
                curveTo(7.548f, 21.75f, 8.067f, 21.75f, 8.919f, 21.75f)
                lineTo(10f, 21.75f)
                curveTo(11.294f, 21.75f, 12.489f, 21.322f, 13.45f, 20.6f)
                curveTo(13.782f, 20.351f, 13.849f, 19.881f, 13.6f, 19.55f)
                curveTo(13.351f, 19.219f, 12.881f, 19.152f, 12.55f, 19.401f)
                curveTo(11.839f, 19.934f, 10.958f, 20.25f, 10f, 20.25f)
                horizontalLineTo(9f)
                curveTo(8.042f, 20.25f, 7.651f, 20.248f, 7.335f, 20.198f)
                curveTo(5.517f, 19.91f, 4.09f, 18.483f, 3.802f, 16.665f)
                curveTo(3.752f, 16.349f, 3.75f, 15.958f, 3.75f, 15f)
                lineTo(3.75f, 9f)
                curveTo(3.75f, 8.042f, 3.752f, 7.651f, 3.802f, 7.335f)
                curveTo(4.09f, 5.517f, 5.517f, 4.09f, 7.335f, 3.802f)
                curveTo(7.651f, 3.752f, 8.042f, 3.75f, 9f, 3.75f)
                horizontalLineTo(10f)
                curveTo(10.958f, 3.75f, 11.839f, 4.066f, 12.55f, 4.599f)
                curveTo(12.881f, 4.848f, 13.351f, 4.781f, 13.6f, 4.45f)
                curveTo(13.849f, 4.119f, 13.782f, 3.649f, 13.45f, 3.4f)
                curveTo(12.489f, 2.678f, 11.294f, 2.25f, 10f, 2.25f)
                lineTo(8.919f, 2.25f)
                close()
            }
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(17.466f, 7.412f)
                curveTo(17.141f, 7.155f, 16.67f, 7.209f, 16.412f, 7.534f)
                curveTo(16.155f, 7.859f, 16.209f, 8.33f, 16.534f, 8.588f)
                lineTo(18.297f, 9.986f)
                curveTo(19.001f, 10.544f, 19.483f, 10.927f, 19.81f, 11.25f)
                lineTo(7f, 11.25f)
                curveTo(6.586f, 11.25f, 6.25f, 11.586f, 6.25f, 12f)
                curveTo(6.25f, 12.414f, 6.586f, 12.75f, 7f, 12.75f)
                lineTo(19.81f, 12.75f)
                curveTo(19.483f, 13.073f, 19.001f, 13.456f, 18.297f, 14.014f)
                lineTo(16.534f, 15.412f)
                curveTo(16.209f, 15.67f, 16.155f, 16.141f, 16.412f, 16.466f)
                curveTo(16.67f, 16.791f, 17.141f, 16.845f, 17.466f, 16.588f)
                lineTo(19.265f, 15.161f)
                curveTo(19.937f, 14.628f, 20.492f, 14.188f, 20.888f, 13.795f)
                curveTo(21.293f, 13.39f, 21.629f, 12.942f, 21.721f, 12.369f)
                curveTo(21.74f, 12.247f, 21.75f, 12.123f, 21.75f, 12f)
                curveTo(21.75f, 11.877f, 21.74f, 11.753f, 21.721f, 11.631f)
                curveTo(21.629f, 11.058f, 21.293f, 10.61f, 20.888f, 10.205f)
                curveTo(20.492f, 9.812f, 19.937f, 9.372f, 19.265f, 8.839f)
                lineTo(17.466f, 7.412f)
                close()
            }
        }.build()

        return _Logout!!
    }

@Suppress("ObjectPropertyName")
private var _Logout: ImageVector? = null
