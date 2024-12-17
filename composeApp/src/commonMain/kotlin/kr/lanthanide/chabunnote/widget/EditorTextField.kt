package kr.lanthanide.chabunnote.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.lanthanide.chabunnote.util.indexesOf

@Composable
fun LineNumberedTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default
) {
    val textMeasurer = rememberTextMeasurer()
    val lineHeight = textMeasurer.measure(
        text = AnnotatedString("Test"),
        style = textStyle
    ).size.height

    var textFieldWidth by remember { mutableStateOf(0) }

    val lineLengths = remember(text, textMeasurer, textStyle, textFieldWidth) {
        val layoutResult = textMeasurer.measure(
            text = AnnotatedString(text),
            style = textStyle,
            constraints = Constraints.fixedWidth(textFieldWidth)
        )

        val newlineIndexes = text.indexesOf("\n")
        // offset between newlines and layout lines
        var lineOffset = 0
        mutableListOf<Int>().apply {
            for (i in newlineIndexes.indices) {
                var lineLength = 1
                while (layoutResult.getLineEnd(i + lineOffset) < newlineIndexes[i]) {
                    lineOffset++
                    lineLength++
                }
                add(lineLength)
            }
            add(1)
        }
    }
    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(end = 4.dp)
                .width(IntrinsicSize.Min)
        ) {
            for (i in 1..lineLengths.size) {
                Text(
                    text = "$i",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray
                    ),
                    modifier = Modifier.height(((lineHeight- 2) * lineLengths[i-1]).dp).width(30.dp).align(
                        Alignment.CenterHorizontally),
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(5.dp)
                .padding(horizontal = 2.dp)
        )
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
                .onGloballyPositioned { coord -> textFieldWidth = coord.size.width }
                .fillMaxHeight().fillMaxWidth(),
            textStyle = textStyle
        )
    }
}