package com.chilate.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Created by Kevel on 4/21/2023.
 */

private val DarkColorPalette = darkColorScheme(
    primary = MintJulep,
    onPrimary = Drab,
    primaryContainer = Bianca,
    onPrimaryContainer = EerieBlack,
    inversePrimary = AtomicTangerine,
    secondary = JuneBud,
    onSecondary = EerieBlack,
    secondaryContainer = SoftPeach,
    onSecondaryContainer = EerieBlack,
    tertiary = Boulder,
    onTertiary = Woodland,
    tertiaryContainer = SoftPeach,
    onTertiaryContainer = EerieBlack,
    background = Bianca,
    onBackground = EerieBlack,
    surface = DenimBlue,
    onSurface = EerieBlack,
    surfaceVariant = Drab,
    onSurfaceVariant = EerieBlack,
    surfaceTint = JuneBud,
    inverseSurface = EerieBlack,
    inverseOnSurface = Snow,
    error = RedSalsa,
    onError = Snow,
    errorContainer = RedSalsa,
    onErrorContainer = Snow,
    outline = EerieBlack,
    outlineVariant = SoftPeach,
    scrim = Color.Transparent
)

private val LightColorPalette = lightColorScheme(
    primary = MintJulep,
    onPrimary = Drab,
    primaryContainer = Bianca,
    onPrimaryContainer = EerieBlack,
    inversePrimary = Snow,
    secondary = JuneBud,
    onSecondary = EerieBlack,
    secondaryContainer = SoftPeach,
    onSecondaryContainer = EerieBlack,
    tertiary = Boulder,
    onTertiary = Woodland,
    tertiaryContainer = SoftPeach,
    onTertiaryContainer = EerieBlack,
    background = Bianca,
    onBackground = EerieBlack,
    surface = Snow,
    onSurface = EerieBlack,
    surfaceVariant = Drab,
    onSurfaceVariant = EerieBlack,
    surfaceTint = JuneBud,
    inverseSurface = EerieBlack,
    inverseOnSurface = Snow,
    error = RedSalsa,
    onError = Snow,
    errorContainer = RedSalsa,
    onErrorContainer = Snow,
    outline = EerieBlack,
    outlineVariant = SoftPeach,
    scrim = Color.Transparent
)

@Composable
fun PokeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = PokeTypography,
        content = content
    )
}