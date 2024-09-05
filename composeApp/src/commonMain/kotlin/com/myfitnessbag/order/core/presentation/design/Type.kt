package com.myfitnessbag.order.core.presentation.design

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.myfitnessbag.order.core.domain.model.AppLocales
import myfitnessbag.composeapp.generated.resources.Res
import myfitnessbag.composeapp.generated.resources.lota_black
import myfitnessbag.composeapp.generated.resources.lota_bold
import myfitnessbag.composeapp.generated.resources.lota_light
import myfitnessbag.composeapp.generated.resources.lota_regular
import myfitnessbag.composeapp.generated.resources.lota_semibold
import myfitnessbag.composeapp.generated.resources.marai_bold
import myfitnessbag.composeapp.generated.resources.marai_light
import myfitnessbag.composeapp.generated.resources.marai_regular
import org.jetbrains.compose.resources.Font

val lotaFontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.lota_bold, FontWeight.Bold),
        Font(Res.font.lota_light, FontWeight.Light),
        Font(Res.font.lota_regular, FontWeight.Normal),
        Font(Res.font.lota_semibold, FontWeight.SemiBold),
        Font(Res.font.lota_black, FontWeight.Black),
    )
val maraiFontFamily
    @Composable
    get() =
        FontFamily(
            Font(Res.font.marai_bold, FontWeight.Bold),
            Font(Res.font.marai_light, FontWeight.Light),
            Font(Res.font.marai_regular, FontWeight.Normal),
        )

@Composable
fun appTypography(
    lang: String = AppLocales.En.code,
): Typography {
    val family = when (lang) {
        "ar" -> maraiFontFamily
        else -> lotaFontFamily
    }
    return Typography(
        displayLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = -0.25.sp
        ),
        displayMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),
        headlineLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        titleLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        labelLarge = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = family,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )

}