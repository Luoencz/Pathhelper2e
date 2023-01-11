package views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondaryTraits(creatureTraits: SnapshotStateList<String>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        //modifier = Modifier.height(100.dp)
    ) {
        items(creatureTraits.size + 1) { index ->
            if (index < creatureTraits.size) {
                OutlinedTextField(
                    creatureTraits[index],
                    { creatureTraits[index] = it },
                    modifier = Modifier.padding(end = 10.dp)
                )
            }

            if (index == creatureTraits.size) {
                Column(verticalArrangement = Arrangement.Top) {
                    Button(
                        onClick = { creatureTraits.add("") },
                        Modifier.size(27.5f.dp),
                        contentPadding = PaddingValues(2.dp),
                    ) {
                        Text(text = "+", textAlign = TextAlign.Center, fontSize = 7.sp)
                    }
                    Button(
                        onClick = { creatureTraits.removeAt(creatureTraits.size - 1) },
                        Modifier.size(27.5f.dp),
                        contentPadding = PaddingValues(2.dp),
                    ) {
                        Text("-", textAlign = TextAlign.Center, fontSize = 7.sp)
                    }
                }
            }
        }
    }
}