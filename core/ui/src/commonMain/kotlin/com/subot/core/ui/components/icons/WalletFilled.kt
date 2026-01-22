package com.subot.core.ui.components.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Hicon.WalletFilled: ImageVector
    get() {
        if (_WalletFilled != null) {
            return _WalletFilled!!
        }
        _WalletFilled = ImageVector.Builder(
            name = "WalletFilled",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(18.994f, 4.642f)
                curveTo(18.381f, 4.449f, 17.692f, 4.355f, 16.896f, 4.306f)
                curveTo(15.986f, 4.25f, 14.88f, 4.25f, 13.523f, 4.25f)
                horizontalLineTo(10.461f)
                curveTo(8.857f, 4.25f, 7.6f, 4.25f, 6.596f, 4.345f)
                curveTo(5.573f, 4.443f, 4.735f, 4.645f, 3.996f, 5.097f)
                curveTo(3.222f, 5.571f, 2.572f, 6.222f, 2.097f, 6.996f)
                curveTo(2.081f, 7.023f, 2.064f, 7.051f, 2.048f, 7.078f)
                curveTo(2.125f, 5.635f, 2.327f, 4.679f, 2.855f, 3.923f)
                curveTo(3.13f, 3.527f, 3.465f, 3.178f, 3.844f, 2.891f)
                curveTo(5.02f, 2f, 6.698f, 2f, 10.053f, 2f)
                horizontalLineTo(15.422f)
                curveTo(17.109f, 2f, 17.953f, 2f, 18.477f, 2.546f)
                curveTo(18.879f, 2.966f, 18.973f, 3.582f, 18.994f, 4.642f)
                close()
            }
            path(fill = SolidColor(Color(0xFF040000))) {
                moveTo(2.737f, 7.388f)
                curveTo(2f, 8.59f, 2f, 10.227f, 2f, 13.5f)
                curveTo(2f, 16.773f, 2f, 18.41f, 2.737f, 19.612f)
                curveTo(3.149f, 20.285f, 3.715f, 20.851f, 4.388f, 21.263f)
                curveTo(5.59f, 22f, 7.227f, 22f, 10.5f, 22f)
                horizontalLineTo(13.5f)
                curveTo(16.773f, 22f, 18.41f, 22f, 19.612f, 21.263f)
                curveTo(20.285f, 20.851f, 20.851f, 20.285f, 21.263f, 19.612f)
                curveTo(21.747f, 18.823f, 21.913f, 17.846f, 21.97f, 16.341f)
                horizontalLineTo(15.637f)
                curveTo(13.716f, 16.341f, 12.159f, 14.784f, 12.159f, 12.864f)
                curveTo(12.159f, 10.943f, 13.716f, 9.386f, 15.637f, 9.386f)
                horizontalLineTo(21.878f)
                curveTo(21.778f, 8.559f, 21.595f, 7.929f, 21.263f, 7.388f)
                curveTo(20.851f, 6.715f, 20.285f, 6.149f, 19.612f, 5.737f)
                curveTo(19.421f, 5.619f, 19.218f, 5.521f, 19f, 5.438f)
                curveTo(17.847f, 5f, 16.252f, 5f, 13.5f, 5f)
                horizontalLineTo(10.5f)
                curveTo(7.227f, 5f, 5.59f, 5f, 4.388f, 5.737f)
                curveTo(3.715f, 6.149f, 3.149f, 6.715f, 2.737f, 7.388f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF040000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(21.978f, 10.886f)
                horizontalLineTo(15.637f)
                curveTo(14.544f, 10.886f, 13.659f, 11.772f, 13.659f, 12.864f)
                curveTo(13.659f, 13.956f, 14.544f, 14.841f, 15.637f, 14.841f)
                horizontalLineTo(21.998f)
                curveTo(22f, 14.427f, 22f, 13.981f, 22f, 13.5f)
                curveTo(22f, 12.483f, 22f, 11.624f, 21.978f, 10.886f)
                close()
                moveTo(15.637f, 12.114f)
                curveTo(15.222f, 12.114f, 14.887f, 12.449f, 14.887f, 12.864f)
                curveTo(14.887f, 13.278f, 15.222f, 13.614f, 15.637f, 13.614f)
                horizontalLineTo(18.364f)
                curveTo(18.778f, 13.614f, 19.114f, 13.278f, 19.114f, 12.864f)
                curveTo(19.114f, 12.449f, 18.778f, 12.114f, 18.364f, 12.114f)
                horizontalLineTo(15.637f)
                close()
            }
        }.build()

        return _WalletFilled!!
    }

@Suppress("ObjectPropertyName")
private var _WalletFilled: ImageVector? = null
