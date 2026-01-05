package com.sukarobot.subot.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.FAQCircle: ImageVector
    get() {
        if (_FAQCircle != null) {
            return _FAQCircle!!
        }
        _FAQCircle = ImageVector.Builder(
            name = "FAQCircle",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(10.047f, 8.231f)
                curveTo(10.559f, 6.694f, 12.587f, 6.365f, 13.559f, 7.661f)
                curveTo(14.128f, 8.421f, 14.088f, 9.475f, 13.463f, 10.189f)
                lineTo(12.544f, 11.239f)
                curveTo(11.71f, 12.193f, 11.25f, 13.417f, 11.25f, 14.683f)
                verticalLineTo(15f)
                curveTo(11.25f, 15.414f, 11.586f, 15.75f, 12f, 15.75f)
                curveTo(12.414f, 15.75f, 12.75f, 15.414f, 12.75f, 15f)
                verticalLineTo(14.683f)
                curveTo(12.75f, 13.78f, 13.078f, 12.907f, 13.673f, 12.227f)
                lineTo(14.592f, 11.177f)
                curveTo(15.684f, 9.929f, 15.753f, 8.088f, 14.759f, 6.761f)
                curveTo(13.061f, 4.497f, 9.519f, 5.072f, 8.624f, 7.757f)
                lineTo(8.288f, 8.763f)
                curveTo(8.158f, 9.156f, 8.37f, 9.581f, 8.763f, 9.712f)
                curveTo(9.156f, 9.842f, 9.581f, 9.63f, 9.712f, 9.237f)
                lineTo(10.047f, 8.231f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 1.25f)
                curveTo(6.063f, 1.25f, 1.25f, 6.063f, 1.25f, 12f)
                curveTo(1.25f, 17.937f, 6.063f, 22.75f, 12f, 22.75f)
                curveTo(17.937f, 22.75f, 22.75f, 17.937f, 22.75f, 12f)
                curveTo(22.75f, 6.063f, 17.937f, 1.25f, 12f, 1.25f)
                close()
                moveTo(2.75f, 12f)
                curveTo(2.75f, 6.891f, 6.891f, 2.75f, 12f, 2.75f)
                curveTo(17.109f, 2.75f, 21.25f, 6.891f, 21.25f, 12f)
                curveTo(21.25f, 17.109f, 17.109f, 21.25f, 12f, 21.25f)
                curveTo(6.891f, 21.25f, 2.75f, 17.109f, 2.75f, 12f)
                close()
            }
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(13f, 18f)
                curveTo(13f, 18.552f, 12.552f, 19f, 12f, 19f)
                curveTo(11.448f, 19f, 11f, 18.552f, 11f, 18f)
                curveTo(11f, 17.448f, 11.448f, 17f, 12f, 17f)
                curveTo(12.552f, 17f, 13f, 17.448f, 13f, 18f)
                close()
            }
        }.build()

        return _FAQCircle!!
    }

@Suppress("ObjectPropertyName")
private var _FAQCircle: ImageVector? = null
