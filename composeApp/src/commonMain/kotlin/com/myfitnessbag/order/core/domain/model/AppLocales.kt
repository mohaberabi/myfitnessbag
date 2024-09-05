package com.myfitnessbag.order.core.domain.model

import androidx.compose.ui.unit.LayoutDirection


enum class AppLocales(
    val code: String,
    val label: String,
    val direction: LangDirection,
) {
    En("en", "English", LangDirection.LTR),
    Ar("ar", "عربي", LangDirection.RTL)
}

enum class LangDirection {
    RTL,
    LTR,
}

fun LangDirection.toLayoutDirection(): LayoutDirection = when (this) {
    LangDirection.RTL -> LayoutDirection.Rtl
    LangDirection.LTR -> LayoutDirection.Ltr
}

fun String.toAppLocal() = when (this) {
    "ar" -> AppLocales.Ar
    else -> AppLocales.En
}