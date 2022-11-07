import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun creatureCreatorPage(applicationVM: ApplicationVM) {
    val creatureName = mutableStateOf(TextFieldValue(""))
    val creatureRarity = mutableStateOf("Common")
    val creatureTraits = mutableStateListOf("")


    val showRarity = mutableStateOf(false)
    val rarities = listOf("Common", "Uncommon", "Rare", "Unique")
    val colorOfRarity = mutableMapOf(
        "Common" to Color.White,
        "Uncommon" to Color.Yellow,
        "Rare" to Color.Cyan,
        "Unique" to Color.Magenta
    )

    val exit_button_color = ButtonDefaults.buttonColors(backgroundColor = Color.Red)

    MaterialTheme {
        Column(
            Modifier.padding(15.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextField(
                    value = creatureName.value,
                    onValueChange = { creatureName.value = it },
                    label = { Text("Name") })
                Spacer(Modifier.weight(1f))
                Button(onClick = {applicationVM.page.value = "HomePage"}, content = { Text("x") }, colors = exit_button_color)
            }

            Row { Text("TRAITS") }

            Row(Modifier.padding(vertical = 3.dp)) {
                rarity(creatureRarity, colorOfRarity, showRarity, rarities)
                secondaryTraits(creatureTraits)
            }
        }
    }
}

@Composable
private fun rarity(
    creatureRarity: MutableState<String>,
    colorOfRarity: MutableMap<String, Color>,
    showRarity: MutableState<Boolean>,
    rarities: List<String>
) {
    Box(Modifier.wrapContentSize()) {
        OutlinedTextField(
            creatureRarity.value,
            { creatureRarity.value = it },
            Modifier.width(180.dp),
            readOnly = true,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = colorOfRarity[creatureRarity.value]!!)
        )
        Button(onClick = { showRarity.value = !showRarity.value }, content = { Text("^") },
            modifier = Modifier.align(Alignment.CenterEnd).padding(3.dp)
        )
        DropdownMenu(
            expanded = showRarity.value,
            onDismissRequest = { showRarity.value = false }) { //TODO resolve dismiss request problem
            rarities.forEach {
                DropdownMenuItem(onClick = {
                    creatureRarity.value = it
                    showRarity.value = false
                }) {
                    Text(it)
                }
            }
        }
    }
}

@Composable
private fun secondaryTraits(creatureTraits: SnapshotStateList<String>) {
    creatureTraits.forEachIndexed { index, _ ->
        if (index != creatureTraits.size -1) {
        OutlinedTextField(
            creatureTraits[index], { creatureTraits[index] = it }, modifier = Modifier
                .width(180.dp)
                .padding(horizontal = 5.dp)
        )}
        else {
            OutlinedTextField(
                creatureTraits[index], { creatureTraits[index] = it }, modifier = Modifier
                    .width(180.dp)
                    .padding(start = 5.dp)
            )
        }

    }
    Column(verticalArrangement = Arrangement.Top) {
        Button(onClick = { creatureTraits.add("") }, content = { Text(text = "+", textAlign = TextAlign.Center, modifier = Modifier.size(15.dp))}, modifier = Modifier.size(27.5f.dp))
        Button(onClick = { creatureTraits.removeAt(creatureTraits.size - 1) }, content = { Text("-", textAlign = TextAlign.Center) }, modifier = Modifier.size(27.5f.dp))
    }
}
