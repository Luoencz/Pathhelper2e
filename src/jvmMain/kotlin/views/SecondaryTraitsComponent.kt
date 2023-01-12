package views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.*

@Composable
fun SecondaryTraits(creatureTraits: SnapshotStateList<String>) {
    HorizontalFlow {
        var i = 0
        while (i <= creatureTraits.size) {
            var index = i
            if (index < creatureTraits.size) {
                OutlinedTextField(
                    creatureTraits[index],
                    { creatureTraits[index] = it },
                    modifier = Modifier.padding(end = 10.dp).width(150.dp)
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
            i++
        }
    }
}