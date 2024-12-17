package kr.lanthanide.chabunnote

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*

@Composable
fun ChabunTheme(isDarkMode: Boolean = false, content: @Composable () -> Unit) {
    val palette = if (isDarkMode) chabunDarkColors else chabunColors
    MaterialTheme(
        colors = palette.switch(),
        typography = chabunTypo,
        shapes = chabunShapes,
        content = content
    )
}

private val chabunColors = lightColors(
    primary = Color(0xff4633ed),
    primaryVariant = Color(0xff2e35b1),
    secondary = Color(0xFF03DAC6),
    secondaryVariant = Color(0xFF018786),
    background = Color.White,
    surface = Color(0xFFF5F5F5),
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color(0xff2d2828)
)
private val chabunDarkColors = darkColors(
    primary = Color(0xff4633ed),
    primaryVariant = Color(0xff2e35b1),
    secondary = Color(0xFF03DAC6),
    secondaryVariant = Color(0xFF018786),
    background = Color(0xFF121212),
    surface = Color(0xff353535),
    error = Color(0xFFB00020),
    onPrimary = Color(0xFFF5F5F5),
    onSecondary = Color(0xFFF5F5F5),
    onBackground = Color(0xFFF5F5F5),
    onSurface = Color(0xFFF5F5F5),
    onError = Color(0xff2d2828)
)

private val chabunTypo = Typography()
//private val chabunShapes = Shapes

private val chabunShapes = Shapes()

@Composable
fun Colors.switch() = copy(
    primary = animateColor(primary),
    primaryVariant = animateColor(primaryVariant),
    secondary = animateColor(secondary),
    secondaryVariant = animateColor(secondaryVariant),
    background = animateColor(background),
    surface = animateColor(surface),
    error = animateColor(error),
    onPrimary = animateColor(onPrimary),
    onSecondary = animateColor(onSecondary),
    onBackground = animateColor(onBackground),
    onSurface = animateColor(onSurface),
    onError = animateColor(onError)
)

@Composable
private fun animateColor(target: Color) =
    animateColorAsState(
        targetValue = target,
        animationSpec = tween(durationMillis = 500)
    ).value

fun mixColor(color1: Color, color2: Color, ratio: Float): Color {
    val inverse = 1 - ratio
    val a = color1.alpha * inverse + color2.alpha * ratio
    val r = color1.red * inverse + color2.red * ratio
    val g = color1.green * inverse + color2.green * ratio
    val b = color1.blue * inverse + color2.blue * ratio
    return Color(a, r, g, b)
}