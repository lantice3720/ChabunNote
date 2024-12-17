package kr.lanthanide.chabunnote.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.lanthanide.chabunnote.modifier.borderBottom
import kr.lanthanide.chabunnote.modifier.borderLeft
import kr.lanthanide.chabunnote.modifier.borderRight
import kr.lanthanide.chabunnote.modifier.borderUp

@Composable
fun Editor(editorState: EditorState) {
    val tabListScroll = rememberScrollState()
    Column {
        // Tab bar
        Row {
            Row(
                modifier = Modifier.horizontalScroll(tabListScroll).height(40.dp)
            ) {
                editorState.tabs.forEachIndexed { index, tabData ->
                    val interactionSource = remember { MutableInteractionSource() }
                    val isHovered by interactionSource.collectIsHoveredAsState()
                    Surface(
                        modifier = Modifier
                            .clickable(interactionSource, null) {
                                editorState.selectedIndex = index
                            }
                            .hoverable(interactionSource = interactionSource)
                            .background(
                                if (index == editorState.selectedIndex) {
                                    MaterialTheme.colors.surface
                                } else {
                                    MaterialTheme.colors.background
                                }
                            )
                            .then(
                                if (index == editorState.selectedIndex)
                                    Modifier.borderUp(MaterialTheme.colors.onSurface, 1.dp)
                                        .borderLeft(MaterialTheme.colors.onSurface, 1.dp)
                                        .borderRight(MaterialTheme.colors.onSurface, 1.dp)
                                else
                                    Modifier.borderBottom(MaterialTheme.colors.onSurface, 1.dp)
                            )
                            .padding(4.dp),
                        color = Color.Transparent
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = tabData.title,
                                color = if (index == editorState.selectedIndex || isHovered) {
                                    MaterialTheme.colors.primary
                                } else {
                                    MaterialTheme.colors.onSurface
                                }
                            )
                            TextButton(
                                onClick = { editorState.removeTab(index) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                if (index == editorState.selectedIndex || isHovered) {
                                    Icon(Icons.Default.Close, "Close")
                                }
                            }
                        }
                    }
                }
                Box (
                    modifier = Modifier.size(40.dp).borderBottom(MaterialTheme.colors.onSurface, 1.dp)
                ) {
                    TextButton(
                        onClick = { editorState.addTab() },
                        modifier = Modifier.size(32.dp).align(Alignment.CenterStart)
                    ) {
                        Icon(Icons.Default.Add, "Add")
                    }
                }
            }
            Box(
                modifier = Modifier.weight(1f)
                    .borderBottom(MaterialTheme.colors.onSurface, 1.dp)
                    .height(40.dp)
            )
        }
        // Editor
        Box(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            if (editorState.tabs.isEmpty()) {
                Text("No tabs", modifier = Modifier.align(Alignment.Center))
                return@Box
            } else {
                LineNumberedTextField(
                    text = editorState.tabs[editorState.selectedIndex].content,
                    onTextChange = { editorState.tabs[editorState.selectedIndex].content = it },
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                        .background(MaterialTheme.colors.surface)
                )
            }
        }
    }
}