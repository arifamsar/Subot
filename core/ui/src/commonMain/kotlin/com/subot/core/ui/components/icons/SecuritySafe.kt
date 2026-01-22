package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.SecuritySafe: ImageVector
    get() {
        if (_SecuritySafe != null) {
            return _SecuritySafe!!
        }
        _SecuritySafe = ImageVector.Builder(
            name = "SecuritySafe",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 7.25f)
                curveTo(10.481f, 7.25f, 9.25f, 8.481f, 9.25f, 10f)
                curveTo(9.25f, 11.259f, 10.096f, 12.32f, 11.25f, 12.646f)
                verticalLineTo(16f)
                curveTo(11.25f, 16.414f, 11.586f, 16.75f, 12f, 16.75f)
                curveTo(12.414f, 16.75f, 12.75f, 16.414f, 12.75f, 16f)
                verticalLineTo(12.646f)
                curveTo(13.904f, 12.32f, 14.75f, 11.259f, 14.75f, 10f)
                curveTo(14.75f, 8.481f, 13.519f, 7.25f, 12f, 7.25f)
                close()
                moveTo(10.75f, 10f)
                curveTo(10.75f, 9.31f, 11.31f, 8.75f, 12f, 8.75f)
                curveTo(12.69f, 8.75f, 13.25f, 9.31f, 13.25f, 10f)
                curveTo(13.25f, 10.69f, 12.69f, 11.25f, 12f, 11.25f)
                curveTo(11.31f, 11.25f, 10.75f, 10.69f, 10.75f, 10f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9.563f, 1.25f)
                curveTo(8.021f, 1.25f, 6.771f, 2.5f, 6.771f, 4.042f)
                curveTo(6.771f, 4.755f, 6.193f, 5.333f, 5.48f, 5.333f)
                horizontalLineTo(5.171f)
                curveTo(3.595f, 5.333f, 2.191f, 6.553f, 2.276f, 8.246f)
                curveTo(2.378f, 10.257f, 2.824f, 13.392f, 4.454f, 16.361f)
                curveTo(4.91f, 17.192f, 5.519f, 18.009f, 6.178f, 18.77f)
                lineTo(6.228f, 18.828f)
                curveTo(7.175f, 19.923f, 7.965f, 20.836f, 8.815f, 21.458f)
                curveTo(9.729f, 22.127f, 10.704f, 22.461f, 12f, 22.461f)
                curveTo(13.296f, 22.461f, 14.272f, 22.127f, 15.185f, 21.458f)
                curveTo(16.035f, 20.836f, 16.825f, 19.923f, 17.773f, 18.828f)
                lineTo(17.822f, 18.77f)
                curveTo(18.481f, 18.009f, 19.09f, 17.192f, 19.547f, 16.361f)
                curveTo(21.23f, 13.293f, 21.65f, 9.827f, 21.733f, 7.666f)
                curveTo(21.797f, 5.98f, 20.403f, 4.75f, 18.818f, 4.75f)
                horizontalLineTo(18.229f)
                curveTo(17.677f, 4.75f, 17.229f, 4.302f, 17.229f, 3.75f)
                curveTo(17.229f, 2.369f, 16.11f, 1.25f, 14.729f, 1.25f)
                horizontalLineTo(9.563f)
                close()
                moveTo(8.271f, 4.042f)
                curveTo(8.271f, 3.328f, 8.85f, 2.75f, 9.563f, 2.75f)
                horizontalLineTo(14.729f)
                curveTo(15.281f, 2.75f, 15.729f, 3.198f, 15.729f, 3.75f)
                curveTo(15.729f, 5.131f, 16.848f, 6.25f, 18.229f, 6.25f)
                horizontalLineTo(18.818f)
                curveTo(19.656f, 6.25f, 20.262f, 6.873f, 20.234f, 7.608f)
                curveTo(20.154f, 9.673f, 19.752f, 12.869f, 18.232f, 15.639f)
                curveTo(17.843f, 16.348f, 17.304f, 17.077f, 16.688f, 17.789f)
                curveTo(15.678f, 18.956f, 14.996f, 19.737f, 14.299f, 20.248f)
                curveTo(13.651f, 20.722f, 12.989f, 20.961f, 12f, 20.961f)
                curveTo(11.011f, 20.961f, 10.349f, 20.722f, 9.701f, 20.248f)
                curveTo(9.004f, 19.737f, 8.322f, 18.956f, 7.312f, 17.789f)
                curveTo(6.696f, 17.077f, 6.158f, 16.348f, 5.769f, 15.639f)
                curveTo(4.29f, 12.945f, 3.87f, 10.062f, 3.774f, 8.171f)
                curveTo(3.738f, 7.451f, 4.331f, 6.833f, 5.171f, 6.833f)
                horizontalLineTo(5.48f)
                curveTo(7.021f, 6.833f, 8.271f, 5.583f, 8.271f, 4.042f)
                close()
            }
        }.build()

        return _SecuritySafe!!
    }

@Suppress("ObjectPropertyName")
private var _SecuritySafe: ImageVector? = null
