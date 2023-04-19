package com.example.figmarelay_test.dropdowntrait

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import com.google.relay.compose.CrossAxisAlignment
import com.google.relay.compose.MainAxisAlignment
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayText

val impact: FontFamily = FontFamily.Default

@Composable
fun DropdownTrait(modifier: Modifier = Modifier, name: String, ) {
    TopLevel(modifier = modifier) {
        Label(name = name)
        Value()
    }
}

@Composable
fun Label(modifier: Modifier = Modifier, name: String) {
    RelayText(
        fontFamily = impact,
        color = Color(
            alpha = 255,
            red = 96,
            green = 27,
            blue = 21
        ),
        height = 1.2197265625.em,
        textAlign = TextAlign.Left,
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
    ) { m, max, over, st ->
        BasicText(
            name,
            modifier = m,
            maxLines = max,
            overflow = over,
            style = st,
        )
    }
}

@Composable
fun Value(modifier: Modifier = Modifier) {
    RelayText(
        color = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        ),
        height = 1.171875.em,
        textAlign = TextAlign.Left,
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
    )
}

@Composable
fun TopLevel(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
        itemSpacing = 4.0,
        clipToParent = false,
        content = content,
        modifier = modifier.fillMaxHeight(1.0f)
    )
}
