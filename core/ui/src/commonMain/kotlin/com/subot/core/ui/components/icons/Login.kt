package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.Login: ImageVector
    get() {
        if (_Login != null) {
            return _Login!!
        }
        _Login = ImageVector.Builder(
            name = "Login",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(15f, 3.75f)
                curveTo(15.958f, 3.75f, 16.349f, 3.752f, 16.665f, 3.802f)
                curveTo(18.483f, 4.09f, 19.91f, 5.517f, 20.198f, 7.335f)
                curveTo(20.248f, 7.651f, 20.25f, 8.042f, 20.25f, 9f)
                verticalLineTo(15f)
                curveTo(20.25f, 15.958f, 20.248f, 16.349f, 20.198f, 16.665f)
                curveTo(19.91f, 18.483f, 18.483f, 19.91f, 16.665f, 20.198f)
                curveTo(16.349f, 20.248f, 15.958f, 20.25f, 15f, 20.25f)
                horizontalLineTo(14f)
                curveTo(13.042f, 20.25f, 12.161f, 19.934f, 11.45f, 19.401f)
                curveTo(11.119f, 19.152f, 10.649f, 19.219f, 10.4f, 19.55f)
                curveTo(10.152f, 19.881f, 10.218f, 20.351f, 10.55f, 20.6f)
                curveTo(11.511f, 21.322f, 12.706f, 21.75f, 14f, 21.75f)
                lineTo(15.081f, 21.75f)
                curveTo(15.933f, 21.75f, 16.452f, 21.75f, 16.899f, 21.679f)
                curveTo(19.36f, 21.289f, 21.289f, 19.36f, 21.679f, 16.899f)
                curveTo(21.75f, 16.452f, 21.75f, 15.933f, 21.75f, 15.081f)
                verticalLineTo(8.919f)
                curveTo(21.75f, 8.067f, 21.75f, 7.548f, 21.679f, 7.101f)
                curveTo(21.289f, 4.64f, 19.36f, 2.71f, 16.899f, 2.321f)
                curveTo(16.452f, 2.25f, 15.933f, 2.25f, 15.081f, 2.25f)
                lineTo(14f, 2.25f)
                curveTo(12.706f, 2.25f, 11.511f, 2.678f, 10.55f, 3.4f)
                curveTo(10.218f, 3.649f, 10.152f, 4.119f, 10.4f, 4.45f)
                curveTo(10.649f, 4.781f, 11.119f, 4.848f, 11.45f, 4.599f)
                curveTo(12.161f, 4.066f, 13.042f, 3.75f, 14f, 3.75f)
                horizontalLineTo(15f)
                close()
            }
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(13.466f, 7.412f)
                curveTo(13.141f, 7.155f, 12.67f, 7.209f, 12.412f, 7.534f)
                curveTo(12.155f, 7.859f, 12.21f, 8.33f, 12.534f, 8.588f)
                lineTo(14.297f, 9.986f)
                curveTo(15.001f, 10.544f, 15.483f, 10.927f, 15.811f, 11.25f)
                lineTo(3f, 11.25f)
                curveTo(2.586f, 11.25f, 2.25f, 11.586f, 2.25f, 12f)
                curveTo(2.25f, 12.414f, 2.586f, 12.75f, 3f, 12.75f)
                lineTo(15.811f, 12.75f)
                curveTo(15.483f, 13.073f, 15.001f, 13.456f, 14.297f, 14.014f)
                lineTo(12.534f, 15.412f)
                curveTo(12.21f, 15.67f, 12.155f, 16.141f, 12.412f, 16.466f)
                curveTo(12.67f, 16.791f, 13.141f, 16.845f, 13.466f, 16.588f)
                lineTo(15.265f, 15.161f)
                curveTo(15.937f, 14.628f, 16.492f, 14.188f, 16.888f, 13.795f)
                curveTo(17.293f, 13.39f, 17.629f, 12.942f, 17.721f, 12.369f)
                curveTo(17.74f, 12.247f, 17.75f, 12.123f, 17.75f, 12f)
                curveTo(17.75f, 11.877f, 17.74f, 11.753f, 17.721f, 11.631f)
                curveTo(17.629f, 11.058f, 17.293f, 10.61f, 16.888f, 10.205f)
                curveTo(16.492f, 9.812f, 15.937f, 9.372f, 15.265f, 8.839f)
                lineTo(13.466f, 7.412f)
                close()
            }
        }.build()

        return _Login!!
    }

@Suppress("ObjectPropertyName")
private var _Login: ImageVector? = null
