package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.ProfileFilled: ImageVector
    get() {
        if (_ProfileFilled != null) {
            return _ProfileFilled!!
        }
        _ProfileFilled = ImageVector.Builder(
            name = "ProfileFilled",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(12f, 2f)
                curveTo(9.377f, 2f, 7.25f, 4.127f, 7.25f, 6.75f)
                curveTo(7.25f, 9.373f, 9.377f, 11.5f, 12f, 11.5f)
                curveTo(14.623f, 11.5f, 16.75f, 9.373f, 16.75f, 6.75f)
                curveTo(16.75f, 4.127f, 14.623f, 2f, 12f, 2f)
                close()
            }
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(9f, 13f)
                curveTo(6.377f, 13f, 4.25f, 15.127f, 4.25f, 17.75f)
                curveTo(4.25f, 20.373f, 6.377f, 22.5f, 9f, 22.5f)
                horizontalLineTo(15f)
                curveTo(17.623f, 22.5f, 19.75f, 20.373f, 19.75f, 17.75f)
                curveTo(19.75f, 15.127f, 17.623f, 13f, 15f, 13f)
                horizontalLineTo(9f)
                close()
            }
        }.build()

        return _ProfileFilled!!
    }

@Suppress("ObjectPropertyName")
private var _ProfileFilled: ImageVector? = null
