package gr.sppzglou.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


// Light Theme Colors
private val LightColorScheme = lightColorScheme(
    primary = NavyBlueLight,              // Main blue
    onPrimary = OnPrimaryLight,
    secondary = SlateLight,               // Soft blue-gray
    onSecondary = OnSecondaryLight,
    tertiary = GoldLight,                 // Gold accent
    onTertiary = OnTertiaryLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight
)

// Dark Theme Colors
private val DarkColorScheme = darkColorScheme(
    primary = NavyBlueDark,          // Main deep blue
    onPrimary = OnPrimaryDark,
    secondary = SlateDark,           // Muted blue-gray
    onSecondary = OnSecondaryDark,
    tertiary = GoldDark,             // Warm gold
    onTertiary = OnTertiaryDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark
)

@Composable
fun LexicaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}