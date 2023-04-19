package components_general

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.google.relay.compose.RelayText
import data.*
import relay_components.impact

@Composable
fun <T: Enum<T>> TextDropdown(
    selected: T, onValueChanged: (T) -> Unit, values: Array<T>, modifier: Modifier = Modifier, showOnlyFirstLetter: Boolean = false
) {
    var showDropdown by remember { mutableStateOf(false) }
    var size_mod = if(showOnlyFirstLetter) Modifier.size(20.dp) else Modifier.size(width = 120.dp, height = 20.dp)
    Box(
        modifier = modifier,
    ) {
        RelayText(
            fontFamily = impact,
            textAlign = if(showOnlyFirstLetter) TextAlign.Center else TextAlign.Left,
            color = Color(
                alpha = 255,
                red = 9,
                green = 39,
                blue = 96
            ),
            height = 1.2197265625.em,
            maxLines = -1,
            modifier = size_mod.padding(
                paddingValues = PaddingValues(
                    start = 4.0.dp,
                    end = 4.0.dp,
                )
            ).wrapContentHeight(align = Alignment.CenterVertically)
        ) { m, max, over, st ->
            BasicText(
                if(showOnlyFirstLetter) selected.name[0].toString() else selected.name,
                modifier = m.clickable { showDropdown = !showDropdown },
                maxLines = max,
                overflow = over,
                style = st,
            )
        }
        DropdownMenu(
            expanded = showDropdown,
            onDismissRequest = { showDropdown = false }) { //TODO resolve dismiss request problem
            values.forEach {
                DropdownMenuItem(onClick = {
                    onValueChanged(it)
                    showDropdown = false
                }) {
                    Text(it.name)
                }
            }
        }
    }
}