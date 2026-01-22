package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.ArrowLeft: ImageVector
    get() {
        if (_ArrowLeft != null) {
            return _ArrowLeft!!
        }
        _ArrowLeft = ImageVector.Builder(
            name = "ArrowLeft",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(14.528f, 7.533f)
                curveTo(14.822f, 7.241f, 14.824f, 6.766f, 14.533f, 6.472f)
                curveTo(14.241f, 6.178f, 13.766f, 6.176f, 13.472f, 6.467f)
                lineTo(11.677f, 8.246f)
                curveTo(11.001f, 8.916f, 10.449f, 9.464f, 10.057f, 9.951f)
                curveTo(9.65f, 10.459f, 9.355f, 10.974f, 9.276f, 11.592f)
                curveTo(9.241f, 11.863f, 9.241f, 12.137f, 9.276f, 12.408f)
                curveTo(9.355f, 13.026f, 9.65f, 13.541f, 10.057f, 14.049f)
                curveTo(10.449f, 14.536f, 11.001f, 15.084f, 11.677f, 15.754f)
                lineTo(13.472f, 17.533f)
                curveTo(13.766f, 17.824f, 14.241f, 17.822f, 14.533f, 17.528f)
                curveTo(14.824f, 17.234f, 14.822f, 16.759f, 14.528f, 16.467f)
                lineTo(12.765f, 14.72f)
                curveTo(12.05f, 14.011f, 11.559f, 13.523f, 11.227f, 13.109f)
                curveTo(10.904f, 12.708f, 10.793f, 12.45f, 10.764f, 12.219f)
                curveTo(10.745f, 12.073f, 10.745f, 11.927f, 10.764f, 11.781f)
                curveTo(10.793f, 11.55f, 10.904f, 11.292f, 11.227f, 10.891f)
                curveTo(11.559f, 10.477f, 12.05f, 9.989f, 12.765f, 9.28f)
                lineTo(14.528f, 7.533f)
                close()
            }
        }.build()

        return _ArrowLeft!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowLeft: ImageVector? = null
