package views

import models.CreatureVM
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup

@Composable
fun LevelChoice(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    var popupControl by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center, modifier = Modifier) {
        Button(
            onClick = { popupControl = !popupControl },
            content = { Text(
                creatureVM.creatureLevel.toString(),
                Modifier
                    .size(50.dp, 30.dp)
                    .background(Color.Gray)
                    .border(1.dp, Color.Gray)
                    .padding(3.dp,0.dp,0.dp,0.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Left,
            ) },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(3.dp)
                .size(30f.dp),
            contentPadding = PaddingValues(2.dp)
        )
    }

    if (popupControl)
        Popup(focusable = true, onDismissRequest = { popupControl = false }) {
            Column(
                Modifier
                    .padding(end = 30.dp, top = 60.dp)
                    .background(Color.Gray)
            ) {
                Text(
                    "Choose level of the creature!",
                    Modifier
                        .padding(bottom = 5.dp)
                        .background(Color.Gray)
                )
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(70.dp),
                    // modifier = Modifier.height(100.dp)
                ) {
                    items(26) { index ->
                        Button(onClick = {
                            creatureVM.creatureLevel = index - 1
                            popupControl = false
                        }, Modifier.padding(5.dp)) {
                            Text((index - 1).toString())
                        }
                    }
                }
            }
        }
}