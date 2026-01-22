package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.MoonOutlined: ImageVector
    get() {
        if (_MoonOutlined != null) {
            return _MoonOutlined!!
        }
        _MoonOutlined = ImageVector.Builder(
            name = "MoonOutlined",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(11.671f, 4.035f)
                curveTo(11.396f, 5.889f, 11.051f, 8.778f, 12.919f, 12.014f)
                curveTo(14.787f, 15.25f, 17.462f, 16.395f, 19.205f, 17.084f)
                curveTo(19.265f, 17.108f, 19.325f, 17.132f, 19.385f, 17.155f)
                curveTo(19.745f, 17.297f, 20.092f, 17.433f, 20.362f, 17.567f)
                curveTo(20.522f, 17.646f, 20.692f, 17.743f, 20.84f, 17.863f)
                curveTo(20.985f, 17.982f, 21.163f, 18.168f, 21.247f, 18.441f)
                curveTo(21.438f, 19.06f, 21.014f, 19.53f, 20.754f, 19.766f)
                curveTo(20.446f, 20.047f, 19.979f, 20.351f, 19.377f, 20.699f)
                curveTo(14.235f, 23.667f, 7.66f, 21.906f, 4.692f, 16.764f)
                curveTo(1.723f, 11.622f, 3.485f, 5.048f, 8.627f, 2.079f)
                curveTo(9.229f, 1.731f, 9.726f, 1.479f, 10.123f, 1.352f)
                curveTo(10.457f, 1.246f, 11.076f, 1.114f, 11.517f, 1.589f)
                curveTo(11.711f, 1.798f, 11.783f, 2.045f, 11.814f, 2.23f)
                curveTo(11.844f, 2.418f, 11.842f, 2.614f, 11.831f, 2.792f)
                curveTo(11.813f, 3.092f, 11.757f, 3.461f, 11.7f, 3.844f)
                curveTo(11.69f, 3.907f, 11.681f, 3.971f, 11.671f, 4.035f)
                close()
                moveTo(10.318f, 2.883f)
                curveTo(10.091f, 2.983f, 9.785f, 3.142f, 9.377f, 3.378f)
                curveTo(4.952f, 5.932f, 3.437f, 11.59f, 5.991f, 16.014f)
                curveTo(8.545f, 20.438f, 14.202f, 21.954f, 18.627f, 19.4f)
                curveTo(19.035f, 19.164f, 19.326f, 18.978f, 19.526f, 18.832f)
                curveTo(19.345f, 18.751f, 19.121f, 18.663f, 18.844f, 18.554f)
                curveTo(18.783f, 18.53f, 18.72f, 18.505f, 18.653f, 18.479f)
                curveTo(16.834f, 17.76f, 13.752f, 16.456f, 11.62f, 12.764f)
                curveTo(9.488f, 9.071f, 9.9f, 5.75f, 10.187f, 3.815f)
                curveTo(10.198f, 3.745f, 10.208f, 3.677f, 10.217f, 3.612f)
                curveTo(10.261f, 3.318f, 10.297f, 3.08f, 10.318f, 2.883f)
                close()
                moveTo(10.718f, 2.748f)
                curveTo(10.718f, 2.749f, 10.711f, 2.75f, 10.699f, 2.75f)
                curveTo(10.713f, 2.748f, 10.719f, 2.748f, 10.718f, 2.748f)
                close()
                moveTo(19.832f, 18.569f)
                curveTo(19.838f, 18.558f, 19.842f, 18.553f, 19.843f, 18.553f)
                curveTo(19.844f, 18.553f, 19.841f, 18.558f, 19.832f, 18.569f)
                close()
            }
        }.build()

        return _MoonOutlined!!
    }

@Suppress("ObjectPropertyName")
private var _MoonOutlined: ImageVector? = null
