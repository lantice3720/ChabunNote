package kr.lanthanide.chabunnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

import kr.lanthanide.chabunnote.widget.Editor
import kr.lanthanide.chabunnote.widget.EditorState
import kr.lanthanide.chabunnote.widget.ToolWindow

@Composable
@Preview
fun App() {
    val darkMode = remember { mutableStateOf(false) }
    ChabunTheme(
        isDarkMode = darkMode.value
    ) {
        val editorState = remember { EditorState() }
        Column {
            TextButton(
                onClick = { darkMode.value = !darkMode.value }
            ) {
                Icon(Icons.Outlined.DarkMode, "Dark Mode")
                Text("Dark Mode: ${darkMode.value}")
            }
            Row {
                ToolWindow()
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Editor(editorState)
                }
                ToolWindow(true)
            }
        }
    }
}