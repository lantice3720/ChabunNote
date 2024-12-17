package kr.lanthanide.chabunnote.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

fun Modifier.borderUp(color: Color, width: Dp) = this.drawBehind {
    drawLine(
        color = color,
        start = Offset(0f, width.toPx() / 2),
        end = Offset(size.width, width.toPx() / 2),
        strokeWidth = width.toPx())
}

fun Modifier.borderLeft(color: Color, width: Dp) = this.drawBehind {
    drawLine(
        color = color,
        start = Offset(width.toPx() / 2, 0f),
        end = Offset(width.toPx() / 2, size.height),
        strokeWidth = width.toPx())
}

fun Modifier.borderRight(color: Color, width: Dp) = this.drawBehind {
    drawLine(
        color = color,
        start = Offset(size.width - width.toPx() / 2, 0f),
        end = Offset(size.width - width.toPx() / 2, size.height),
        strokeWidth = width.toPx())
}

fun Modifier.borderBottom(color: Color, width: Dp) = this.drawBehind {
    drawLine(
        color = color,
        start = Offset(0f, size.height - width.toPx() / 2),
        end = Offset(size.width, size.height - width.toPx() / 2),
        strokeWidth = width.toPx())
}