package components_general

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.dp
import data.*

@Composable
fun <T: Enum<T>> TextDropdown(
    selected: T, onValueChanged: (T) -> Unit, values: Array<T>, modifier: Modifier = Modifier, showOnlyFirstLetter: Boolean = false,
    textStyle: TextStyle? = null
) {
    var showDropdown by remember { mutableStateOf(false) }
    var size_mod = if(showOnlyFirstLetter) Modifier.size(20.dp) else Modifier.size(width = 120.dp, height = 20.dp)
    Box(
        modifier = modifier.composed { size_mod },
        contentAlignment = if (showOnlyFirstLetter) Alignment.Center else Alignment.CenterStart
    ) {
        Text(
            text = if(showOnlyFirstLetter) AnnotatedString(selected.name[0].toString()) else AnnotatedString(selected.name),
            overflow = TextOverflow.Clip,
            textAlign = if(showOnlyFirstLetter) TextAlign.Center else TextAlign.Left,
            style = InteractiveTextStyle,
            modifier = Modifier.clickable { showDropdown = !showDropdown },
        )
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