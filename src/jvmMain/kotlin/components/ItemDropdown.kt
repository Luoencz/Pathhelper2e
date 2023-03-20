package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface ColorDropdownItem : DropdownItem {
    val color: Color
}

interface DropdownItem {
    val name: String
}

interface SpecialNameDropdownItem : DropdownItem {
    val altName: String
}
@Composable
fun <T : DropdownItem> ItemDropdown(
    selected: T, onValueChanged: (T) -> Unit, values: Array<T>, size: Dp = 120.dp, color: Color = Color.Transparent, modifier: Modifier = Modifier
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
                if (selected is SpecialNameDropdownItem) selected.altName else selected.name,
                Modifier
                    .wrapContentWidth()
            ) },
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentWidth()
                .widthIn(100.dp)
                .height(25.dp),
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
                    Text(if (it is SpecialNameDropdownItem) it.altName else it.name)
                }
            }
        }
    }
}