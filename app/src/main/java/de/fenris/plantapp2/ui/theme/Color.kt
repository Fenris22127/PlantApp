package de.fenris.plantapp2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import de.fenris.plantapp2.Utils

val DarkOnSurfaceVariant = Color(0xFFBEC9C5)
val LightOnSurfaceVariant = Color(0xFF3F4946)

val DarkOnInverseSurface = Color(0xFF2D332D)
val LightOnInverseSurface = Color(0xFFEDF5ED)

val DarkSurfaceContainer = Color(0xFF1C231C)
val LightSurfaceContainer = Color(0xFFEAF4EA)

@Composable
fun getOnSurfaceVariant(): Color {
    return if (isAppInDarkTheme()) {
        DarkOnSurfaceVariant
    } else {
        LightOnSurfaceVariant
    }
}

@Composable
fun getOnSurface(): Color {
    return Utils
        .getBackgroundColor()
        .compositeOver(MaterialTheme.colorScheme.background)
}

@Composable
fun getOnInverseSurface(): Color {
    return if (isAppInDarkTheme()) {
        DarkOnInverseSurface
    } else {
        LightOnInverseSurface
    }
}

@Composable
fun getSurfaceContainerLow(): Color {
    return if (isAppInDarkTheme()) {
        Color(0xFF191E19)
    } else {
        Color(0xFFF0F8F0)
    }
}

@Composable
fun getSurfaceContainer(): Color {
    return if (isAppInDarkTheme()) {
        DarkSurfaceContainer
    } else {
        LightSurfaceContainer
    }
}

@Composable
fun getTextFieldBackground(): Color {
    return if (isAppInDarkTheme()) {
        MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.5f)
    } else {
        Color.White.copy(alpha = 0.8f)
    }
}

@Composable
fun getSurfaceHigh(): Color {
    return if (isAppInDarkTheme()) {
        Color(0xFF212121)
    } else {
        Color(0xFFFFFFFF)
    }
}

@Composable
fun getSurfaceHighest(): Color {
    return if (isAppInDarkTheme()) {
        Color(0xFF313831)
    } else {
        Color(0xFFDDE6DE)
    }
}

@Composable
fun getNavColor(): Color {
    return if (isAppInDarkTheme()) {
        Color(0xFF1E1E1E)
    } else {
        Color(0xFFFFFFFF)
    }
}