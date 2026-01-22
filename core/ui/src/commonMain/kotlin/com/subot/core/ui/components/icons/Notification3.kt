package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.Notification3: ImageVector
    get() {
        if (_Notification3 != null) {
            return _Notification3!!
        }
        _Notification3 = ImageVector.Builder(
            name = "Notification3",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(11.1f, 1.25f)
                curveTo(7.704f, 1.25f, 5.077f, 4.226f, 5.498f, 7.595f)
                lineTo(5.578f, 8.23f)
                curveTo(5.694f, 9.159f, 5.355f, 10.087f, 4.667f, 10.722f)
                curveTo(3.241f, 12.038f, 2.845f, 14.132f, 3.691f, 15.877f)
                lineTo(3.795f, 16.09f)
                curveTo(4.541f, 17.628f, 6.056f, 18.636f, 7.747f, 18.741f)
                lineTo(7.936f, 19.064f)
                curveTo(8.948f, 20.8f, 10.526f, 21.75f, 12.183f, 21.75f)
                curveTo(13.84f, 21.75f, 15.418f, 20.8f, 16.431f, 19.064f)
                lineTo(16.622f, 18.736f)
                curveTo(18.043f, 18.624f, 19.338f, 17.851f, 20.111f, 16.637f)
                curveTo(21.293f, 14.78f, 20.98f, 12.343f, 19.367f, 10.845f)
                lineTo(19.323f, 10.804f)
                curveTo(18.584f, 10.118f, 18.221f, 9.119f, 18.346f, 8.119f)
                lineTo(18.41f, 7.602f)
                curveTo(18.832f, 4.229f, 16.202f, 1.25f, 12.803f, 1.25f)
                horizontalLineTo(11.1f)
                close()
                moveTo(8.169f, 17.25f)
                curveTo(8.178f, 17.25f, 8.187f, 17.25f, 8.196f, 17.25f)
                horizontalLineTo(16.171f)
                curveTo(16.18f, 17.25f, 16.189f, 17.25f, 16.198f, 17.25f)
                horizontalLineTo(16.262f)
                curveTo(17.309f, 17.25f, 18.284f, 16.715f, 18.846f, 15.832f)
                curveTo(19.639f, 14.585f, 19.429f, 12.95f, 18.346f, 11.944f)
                lineTo(18.302f, 11.903f)
                curveTo(17.21f, 10.889f, 16.673f, 9.412f, 16.857f, 7.933f)
                lineTo(16.922f, 7.416f)
                curveTo(17.232f, 4.938f, 15.3f, 2.75f, 12.803f, 2.75f)
                horizontalLineTo(11.1f)
                curveTo(8.607f, 2.75f, 6.678f, 4.935f, 6.987f, 7.409f)
                lineTo(7.066f, 8.044f)
                curveTo(7.242f, 9.453f, 6.728f, 10.861f, 5.684f, 11.824f)
                curveTo(4.744f, 12.691f, 4.483f, 14.072f, 5.041f, 15.222f)
                lineTo(5.144f, 15.435f)
                curveTo(5.683f, 16.545f, 6.808f, 17.25f, 8.041f, 17.25f)
                horizontalLineTo(8.169f)
                close()
                moveTo(12.183f, 20.25f)
                curveTo(11.262f, 20.25f, 10.28f, 19.788f, 9.52f, 18.75f)
                horizontalLineTo(14.847f)
                curveTo(14.087f, 19.788f, 13.104f, 20.25f, 12.183f, 20.25f)
                close()
            }
        }.build()

        return _Notification3!!
    }

@Suppress("ObjectPropertyName")
private var _Notification3: ImageVector? = null
