package kr.lanthanide.chabunnote.widget

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class EditorState {
    var tabs = mutableStateListOf<TabData>()

    var selectedIndex by mutableStateOf(0)

    fun addTab(title: String = "Anonymous", content: String = "") {
        tabs.add(TabData(title, content))
    }

    fun removeTab(index: Int) {
        if (index < 0 || index >= tabs.size) return

        if (tabs.size == 1) {
            selectedIndex = 0
        } else if (index <= selectedIndex && selectedIndex > 0) {
            selectedIndex--
        }

        tabs.removeAt(index)
    }

    class TabData(title: String, content: String) {
        var title by mutableStateOf(title)
        var content by mutableStateOf(content)

        override fun toString(): String {
            return "TabData(title='$title', content='$content')"
        }
    }
}

