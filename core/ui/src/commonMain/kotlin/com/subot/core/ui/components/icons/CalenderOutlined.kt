package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.CalenderOutlined: ImageVector
    get() {
        if (_CalenderOutlined != null) {
            return _CalenderOutlined!!
        }
        _CalenderOutlined = ImageVector.Builder(
            name = "CalenderOutlined",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(8.25f, 14f)
                curveTo(8.25f, 13.586f, 8.586f, 13.25f, 9f, 13.25f)
                horizontalLineTo(15f)
                curveTo(15.414f, 13.25f, 15.75f, 13.586f, 15.75f, 14f)
                curveTo(15.75f, 14.414f, 15.414f, 14.75f, 15f, 14.75f)
                horizontalLineTo(9f)
                curveTo(8.586f, 14.75f, 8.25f, 14.414f, 8.25f, 14f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(9f, 1.25f)
                curveTo(9.414f, 1.25f, 9.75f, 1.586f, 9.75f, 2f)
                verticalLineTo(3.261f)
                curveTo(10.419f, 3.25f, 11.163f, 3.25f, 11.989f, 3.25f)
                horizontalLineTo(12.011f)
                curveTo(12.837f, 3.25f, 13.581f, 3.25f, 14.25f, 3.261f)
                verticalLineTo(2f)
                curveTo(14.25f, 1.586f, 14.586f, 1.25f, 15f, 1.25f)
                curveTo(15.414f, 1.25f, 15.75f, 1.586f, 15.75f, 2f)
                verticalLineTo(3.314f)
                curveTo(17.262f, 3.408f, 18.423f, 3.653f, 19.38f, 4.348f)
                curveTo(19.868f, 4.703f, 20.297f, 5.132f, 20.652f, 5.62f)
                curveTo(21.454f, 6.724f, 21.656f, 8.101f, 21.721f, 9.974f)
                curveTo(21.75f, 10.832f, 21.75f, 11.829f, 21.75f, 12.989f)
                verticalLineTo(13.011f)
                curveTo(21.75f, 14.171f, 21.75f, 15.168f, 21.721f, 16.026f)
                curveTo(21.656f, 17.899f, 21.454f, 19.276f, 20.652f, 20.38f)
                curveTo(20.297f, 20.868f, 19.868f, 21.297f, 19.38f, 21.652f)
                curveTo(18.276f, 22.454f, 16.899f, 22.656f, 15.026f, 22.721f)
                curveTo(14.168f, 22.75f, 13.171f, 22.75f, 12.011f, 22.75f)
                horizontalLineTo(11.955f)
                curveTo(10.118f, 22.75f, 8.679f, 22.75f, 7.536f, 22.626f)
                curveTo(6.371f, 22.5f, 5.427f, 22.238f, 4.62f, 21.652f)
                curveTo(4.132f, 21.297f, 3.703f, 20.868f, 3.348f, 20.38f)
                curveTo(2.762f, 19.573f, 2.5f, 18.629f, 2.374f, 17.463f)
                curveTo(2.25f, 16.321f, 2.25f, 14.882f, 2.25f, 13.045f)
                lineTo(2.25f, 12.989f)
                curveTo(2.25f, 11.829f, 2.25f, 10.832f, 2.279f, 9.974f)
                curveTo(2.344f, 8.101f, 2.546f, 6.724f, 3.348f, 5.62f)
                curveTo(3.703f, 5.132f, 4.132f, 4.703f, 4.62f, 4.348f)
                curveTo(5.577f, 3.653f, 6.738f, 3.408f, 8.25f, 3.314f)
                verticalLineTo(2f)
                curveTo(8.25f, 1.586f, 8.586f, 1.25f, 9f, 1.25f)
                close()
                moveTo(8.25f, 4.817f)
                curveTo(6.888f, 4.911f, 6.102f, 5.126f, 5.502f, 5.562f)
                curveTo(5.141f, 5.824f, 4.824f, 6.141f, 4.562f, 6.502f)
                curveTo(4.126f, 7.102f, 3.911f, 7.888f, 3.817f, 9.25f)
                horizontalLineTo(20.183f)
                curveTo(20.089f, 7.888f, 19.874f, 7.102f, 19.438f, 6.502f)
                curveTo(19.176f, 6.141f, 18.859f, 5.824f, 18.498f, 5.562f)
                curveTo(17.898f, 5.126f, 17.112f, 4.911f, 15.75f, 4.817f)
                verticalLineTo(5f)
                curveTo(15.75f, 5.414f, 15.414f, 5.75f, 15f, 5.75f)
                curveTo(14.586f, 5.75f, 14.25f, 5.414f, 14.25f, 5f)
                verticalLineTo(4.761f)
                curveTo(13.59f, 4.75f, 12.848f, 4.75f, 12f, 4.75f)
                curveTo(11.152f, 4.75f, 10.41f, 4.75f, 9.75f, 4.761f)
                verticalLineTo(5f)
                curveTo(9.75f, 5.414f, 9.414f, 5.75f, 9f, 5.75f)
                curveTo(8.586f, 5.75f, 8.25f, 5.414f, 8.25f, 5f)
                verticalLineTo(4.817f)
                close()
                moveTo(20.239f, 10.75f)
                horizontalLineTo(3.761f)
                curveTo(3.75f, 11.41f, 3.75f, 12.152f, 3.75f, 13f)
                curveTo(3.75f, 14.892f, 3.751f, 16.25f, 3.865f, 17.302f)
                curveTo(3.977f, 18.34f, 4.193f, 18.99f, 4.562f, 19.498f)
                curveTo(4.824f, 19.859f, 5.141f, 20.176f, 5.502f, 20.438f)
                curveTo(6.01f, 20.807f, 6.66f, 21.022f, 7.698f, 21.135f)
                curveTo(8.75f, 21.249f, 10.108f, 21.25f, 12f, 21.25f)
                curveTo(12.848f, 21.25f, 13.592f, 21.25f, 14.252f, 21.239f)
                curveTo(14.256f, 20.764f, 14.271f, 20.415f, 14.321f, 20.101f)
                curveTo(14.71f, 17.64f, 16.64f, 15.71f, 19.101f, 15.321f)
                curveTo(19.415f, 15.271f, 19.764f, 15.256f, 20.239f, 15.252f)
                curveTo(20.25f, 14.592f, 20.25f, 13.848f, 20.25f, 13f)
                curveTo(20.25f, 12.152f, 20.25f, 11.41f, 20.239f, 10.75f)
                close()
                moveTo(20.183f, 16.753f)
                curveTo(19.778f, 16.757f, 19.54f, 16.77f, 19.335f, 16.802f)
                curveTo(17.517f, 17.09f, 16.09f, 18.517f, 15.802f, 20.335f)
                curveTo(15.77f, 20.54f, 15.757f, 20.778f, 15.753f, 21.183f)
                curveTo(17.113f, 21.089f, 17.899f, 20.874f, 18.498f, 20.438f)
                curveTo(18.859f, 20.176f, 19.176f, 19.859f, 19.438f, 19.498f)
                curveTo(19.874f, 18.899f, 20.089f, 18.113f, 20.183f, 16.753f)
                close()
            }
        }.build()

        return _CalenderOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _CalenderOutlined: ImageVector? = null
