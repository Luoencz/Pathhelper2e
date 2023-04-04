package components_general

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.*

@Composable
fun <T : NamedObject> TextDropdown(
    selected: T, onValueChanged: (T) -> Unit, values: Array<T>, size: Modifier = Modifier.size(width = 85.dp, height = 25.dp), color: Color = Color.Transparent, modifier: Modifier = Modifier
) {
    var showDropdown by remember { mutableStateOf(false) }
    //val backgroundColor = if (selected is ColorDropdownItem) selected.color else color

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { showDropdown = !showDropdown },
            content = { Text(
                if (selected is SpecialNameNamedObject) selected.altName else selected.name,
                Modifier
                    .wrapContentWidth()
            ) },
            modifier = size
                .align(Alignment.Center)
                //.wrapContentWidth()
                //.widthIn()
                    ,
            contentPadding = PaddingValues(2.dp)
        )
        DropdownMenu(
            expanded = showDropdown,
            onDismissRequest = { showDropdown = false }) { //TODO resolve dismiss request problem
            values.forEach {
                DropdownMenuItem(onClick = {
                    onValueChanged(it)
                    showDropdown = false
                }) {
                    Text(if (it is SpecialNameNamedObject) it.altName else it.name)
                }
            }
        }
    }
}