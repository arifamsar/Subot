package com.sukarobot.subot.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.EmailOutlined: ImageVector
    get() {
        if (_EmailOutlined != null) {
            return _EmailOutlined!!
        }
        _EmailOutlined = ImageVector.Builder(
            name = "EmailOutlined",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(17.463f, 2.374f)
                curveTo(16.321f, 2.25f, 14.882f, 2.25f, 13.045f, 2.25f)
                horizontalLineTo(10.955f)
                curveTo(9.118f, 2.25f, 7.679f, 2.25f, 6.536f, 2.374f)
                curveTo(5.371f, 2.5f, 4.427f, 2.762f, 3.62f, 3.348f)
                curveTo(3.132f, 3.703f, 2.703f, 4.132f, 2.348f, 4.62f)
                curveTo(2.186f, 4.843f, 2.048f, 5.078f, 1.93f, 5.328f)
                curveTo(1.568f, 6.094f, 1.407f, 6.982f, 1.328f, 8.042f)
                curveTo(1.25f, 9.093f, 1.25f, 10.382f, 1.25f, 11.972f)
                verticalLineTo(12.045f)
                curveTo(1.25f, 13.882f, 1.25f, 15.321f, 1.374f, 16.463f)
                curveTo(1.5f, 17.629f, 1.762f, 18.573f, 2.348f, 19.38f)
                curveTo(2.703f, 19.868f, 3.132f, 20.297f, 3.62f, 20.652f)
                curveTo(4.427f, 21.238f, 5.371f, 21.5f, 6.536f, 21.626f)
                curveTo(7.679f, 21.75f, 9.118f, 21.75f, 10.955f, 21.75f)
                horizontalLineTo(13.045f)
                curveTo(14.882f, 21.75f, 16.321f, 21.75f, 17.463f, 21.626f)
                curveTo(18.629f, 21.5f, 19.573f, 21.238f, 20.38f, 20.652f)
                curveTo(20.868f, 20.297f, 21.297f, 19.868f, 21.652f, 19.38f)
                curveTo(22.238f, 18.573f, 22.5f, 17.629f, 22.626f, 16.463f)
                curveTo(22.75f, 15.321f, 22.75f, 13.882f, 22.75f, 12.045f)
                verticalLineTo(11.971f)
                curveTo(22.75f, 10.371f, 22.75f, 9.075f, 22.67f, 8.019f)
                curveTo(22.59f, 6.955f, 22.425f, 6.064f, 22.055f, 5.295f)
                curveTo(21.94f, 5.057f, 21.806f, 4.833f, 21.652f, 4.62f)
                curveTo(21.297f, 4.132f, 20.868f, 3.703f, 20.38f, 3.348f)
                curveTo(19.573f, 2.762f, 18.629f, 2.5f, 17.463f, 2.374f)
                close()
                moveTo(4.502f, 4.562f)
                curveTo(5.01f, 4.193f, 5.66f, 3.977f, 6.698f, 3.865f)
                curveTo(7.75f, 3.751f, 9.108f, 3.75f, 11f, 3.75f)
                horizontalLineTo(13f)
                curveTo(14.892f, 3.75f, 16.25f, 3.751f, 17.302f, 3.865f)
                curveTo(18.34f, 3.977f, 18.99f, 4.193f, 19.498f, 4.562f)
                curveTo(19.859f, 4.824f, 20.176f, 5.141f, 20.438f, 5.501f)
                lineTo(18.541f, 7.399f)
                curveTo(16.859f, 9.08f, 15.65f, 10.287f, 14.607f, 11.083f)
                curveTo(13.582f, 11.865f, 12.806f, 12.179f, 12f, 12.179f)
                curveTo(11.194f, 12.179f, 10.418f, 11.865f, 9.393f, 11.083f)
                curveTo(8.35f, 10.287f, 7.141f, 9.08f, 5.459f, 7.399f)
                lineTo(3.562f, 5.501f)
                curveTo(3.824f, 5.141f, 4.141f, 4.824f, 4.502f, 4.562f)
                close()
                moveTo(21.037f, 7.023f)
                curveTo(21.098f, 7.344f, 21.142f, 7.709f, 21.174f, 8.132f)
                curveTo(21.25f, 9.125f, 21.25f, 10.365f, 21.25f, 12f)
                curveTo(21.25f, 13.892f, 21.249f, 15.25f, 21.135f, 16.302f)
                curveTo(21.022f, 17.34f, 20.807f, 17.99f, 20.438f, 18.498f)
                curveTo(20.176f, 18.859f, 19.859f, 19.176f, 19.498f, 19.438f)
                curveTo(18.99f, 19.807f, 18.34f, 20.022f, 17.302f, 20.135f)
                curveTo(16.25f, 20.249f, 14.892f, 20.25f, 13f, 20.25f)
                horizontalLineTo(11f)
                curveTo(9.108f, 20.25f, 7.75f, 20.249f, 6.698f, 20.135f)
                curveTo(5.66f, 20.022f, 5.01f, 19.807f, 4.502f, 19.438f)
                curveTo(4.141f, 19.176f, 3.824f, 18.859f, 3.562f, 18.498f)
                curveTo(3.193f, 17.99f, 2.977f, 17.34f, 2.865f, 16.302f)
                curveTo(2.751f, 15.25f, 2.75f, 13.892f, 2.75f, 12f)
                curveTo(2.75f, 10.376f, 2.75f, 9.142f, 2.824f, 8.153f)
                curveTo(2.856f, 7.721f, 2.901f, 7.349f, 2.963f, 7.023f)
                lineTo(4.439f, 8.5f)
                curveTo(6.071f, 10.132f, 7.351f, 11.411f, 8.483f, 12.275f)
                curveTo(9.642f, 13.159f, 10.734f, 13.679f, 12f, 13.679f)
                curveTo(13.266f, 13.679f, 14.358f, 13.159f, 15.517f, 12.275f)
                curveTo(16.649f, 11.411f, 17.928f, 10.132f, 19.561f, 8.5f)
                lineTo(21.037f, 7.023f)
                close()
            }
        }.build()

        return _EmailOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _EmailOutlined: ImageVector? = null
