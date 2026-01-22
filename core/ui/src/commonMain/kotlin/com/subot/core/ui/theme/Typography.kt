package com.subot.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import subot.core.ui.generated.resources.GoogleSans
import subot.core.ui.generated.resources.Res

@Composable
fun googleSansFontFamily() = FontFamily(
    Font(Res.font.GoogleSans, weight = FontWeight.Normal),
    Font(Res.font.GoogleSans, weight = FontWeight.Medium),
    Font(Res.font.GoogleSans, weight = FontWeight.SemiBold),
    Font(Res.font.GoogleSans, weight = FontWeight.Bold),
    Font(Res.font.GoogleSans, weight = FontWeight.Light)
)

@Composable
fun appTypography(): Typography {
    val fontFamily = googleSansFontFamily()

    return Typography().run {
        copy(
            displayLarge = displayLarge.copy(fontFamily = fontFamily),
            displayMedium = displayMedium.copy(fontFamily = fontFamily),
            displaySmall = displaySmall.copy(fontFamily = fontFamily),
            headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
            titleLarge = titleLarge.copy(fontFamily = fontFamily),
            titleMedium = titleMedium.copy(fontFamily = fontFamily),
            titleSmall = titleSmall.copy(fontFamily = fontFamily),
            bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = bodySmall.copy(fontFamily = fontFamily),
            labelLarge = labelLarge.copy(fontFamily = fontFamily),
            labelMedium = labelMedium.copy(fontFamily = fontFamily),
            labelSmall = labelSmall.copy(fontFamily = fontFamily)
        )
    }
}
