package com.itc.teamsmarties.ui.theme

import androidx.compose.ui.graphics.Color

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val onPrimary: Color,
    val cardColor: Color
){
    object Night: ThemeColors(
        background = Color(0xFF000000),
        surface = Color(0x4DFFFFFF),
        primary = Color(0xFF000000),
        onPrimary = Color(0xFFFFFFFF),
        cardColor = Color(0x0DFFFFFF)
    )
    object Day: ThemeColors(
        background = Color(0xFFFFFFFF),
        surface = Color(0x4DFFFFFF),
        primary = Color(0xFFFFFFFF),
        onPrimary = Color(0xFF000000),
        cardColor = Color(0x0D000000)
    )
}