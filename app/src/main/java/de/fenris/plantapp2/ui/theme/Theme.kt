package de.fenris.plantapp2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import de.fenris.plantapp2.organisation.UserStore

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFA2E5A2),
    onPrimary = Color(0xFF005000),
    secondary = Color(0xFF467246),
    surface = Color(0xFF101610),
    secondaryContainer = Color(0xFF2E5A2E),
    onSecondaryContainer = Color(0xFFCAF6CA),
    onSurface = Color(0xFFDDE6DE),
    onSurfaceVariant = Color(0xFFC0CCC0),
    outline = Color(0xFF8B958C),
    inverseSurface = Color(0xFFDDE6DE),
    inverseOnSurface = Color(0xFF2D332D),
    error = Color(0xFFF2B8B5),
    background = Color(0xFF191919),
    onBackground = Color(0xFFDDE6DE),
    onPrimaryContainer = Color(0xFFD1F3D1),
    outlineVariant = Color(0xFF414B42),
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF2F822F),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFAEDAAE),
    surface = Color(0xFFF8FAF8),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFCAF6CA),
    onSecondaryContainer = Color(0xFF022E02),
    onSurface = Color(0xFF191E19),
    onSurfaceVariant = Color(0xFF414B41),
    outline = Color(0xFF717B71),
    inverseSurface = Color(0xFF2D332D),
    inverseOnSurface = Color(0xFFEDF5ED),
    error = Color(0xFFB3261E),
    background = Color(0xFFF8FAF8),
    onBackground = Color(0xFF191E19),
    onPrimaryContainer = Color(0xFF002200),
    outlineVariant = Color(0xFFC0CCC0),
)

@Composable
fun isAppInDarkTheme(): Boolean {
    val context = LocalContext.current
    val store = UserStore(context)

    val darkModePref = store.getDarkModePreference.collectAsState(initial = true)
    val themeSetByUser = store.getThemeSetByUser.collectAsState(initial = false)
    return if (!themeSetByUser.value) {
        isSystemInDarkTheme()
    } else {
        darkModePref.value
    }

}

@Composable
fun PlantApp2Theme(darkTheme: Boolean = isAppInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
