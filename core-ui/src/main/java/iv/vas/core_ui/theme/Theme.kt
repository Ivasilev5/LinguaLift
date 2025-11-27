package iv.vas.core_ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = LightButtonPrimary,
    onPrimary = LightWhite,
    primaryContainer = LightButtonBack,
    onPrimaryContainer = LightTextLarge,
    secondary = LightButtonChooseLevel,
    onSecondary = LightWhite,
    tertiary = LightCardWord,
    onTertiary = LightWhite,
    background = LightBackground,
    onBackground = LightTextLarge,
    surface = LightWhite,
    onSurface = LightTextLarge,
    surfaceVariant = LightCardWord,
    onSurfaceVariant = LightWhite,
    error = LightError,
    onError = LightWhite,
    outline = LightTextMedium
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkButtonPrimary,
    onPrimary = DarkTextOnButton,
    primaryContainer = DarkButtonBack,
    onPrimaryContainer = DarkTextLarge,
    secondary = DarkButtonPrimary, // Using same as primary for now
    onSecondary = DarkTextOnButton,
    tertiary = DarkCardWord,
    onTertiary = DarkTextLarge,
    background = DarkBackground,
    onBackground = DarkTextLarge,
    surface = DarkButtonBack,
    onSurface = DarkTextLarge,
    surfaceVariant = DarkCardWord,
    onSurfaceVariant = DarkTextLarge,
    error = DarkError,
    onError = DarkTextLarge,
    outline = DarkTextMedium
)

@Composable
fun LinguaLiftTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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