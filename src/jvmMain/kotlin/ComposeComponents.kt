import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup

@Composable
fun AbilityScores(creatureVM: CreatureVM) {
    //val pattern = remember { Regex("^\\d+\$") }
    val pattern = remember { Regex("^[0-9]*\\.*-?[0-9]+\$") }

    Column {
        creatureVM.abilityScores.forEach { abilityInfo ->
            val (ability, info) = abilityInfo // read about Kotlin decompose
            key(ability.name) {
                Row {
                    TextField(
                        value = TextFieldValue(
                            info.score.toString(),
                            selection = TextRange(info.score.toString().length)
                        ),
                        onValueChange = {
                            when {
                                it.text.isEmpty() -> info.score = 0
                                it.text.matches(pattern) -> info.score = it.text.toInt()
                                else -> info.score = info.score
                            }
                        },
                        label = { Text(ability.name) },
                    )

                    val mod = info.modifier
                    Text(text = if (mod < 0) mod.toString() else "+$mod")
                }
            }
        }
    }
}

@Composable
fun experimental_skills_grid(creatureVM: CreatureVM) {
    LazyVerticalGrid(columns = GridCells.Adaptive(160.dp)) {
        items(Skill.values()) { skill ->
            Text(skill.title)
            DropdownWithColor(creatureVM.proficiencies[skill] ?: Proficiency.Untrained, {
                creatureVM.proficiencies[skill] = it
            }, Proficiency.values())
        }
    }
}

@Composable
fun ExperimentalSkillsGrid2(creatureVM: CreatureVM) {
    val items = Skill.values()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp)
    ) {
        items(items.size) { index ->
            Column {
                val skill = items[index]
                Text(
                    skill.title + "        " + creatureVM.skillModifiers[skill],
                    modifier = Modifier.absolutePadding(top = 5.dp)
                )
                DropdownWithColor(creatureVM.proficiencies[skill] ?: Proficiency.Untrained, {
                    creatureVM.proficiencies[skill] = it
                }, Proficiency.values())
            }
        }
    }
}

@Composable
fun skills_grid(creatureVM: CreatureVM) {
    Row {
        Skill.values().forEach { skill ->
            Column {
                Text(skill.title)
                DropdownWithColor(creatureVM.proficiencies[skill] ?: Proficiency.Untrained, {
                    creatureVM.proficiencies[skill] = it
                }, Proficiency.values())
            }
        }
    }
}

@Composable
fun <T : ColorDropdownItem> DropdownWithColor(
    selected: T, onValueChanged: (T) -> Unit, values: Array<T>
) {
    var showDropdown by remember { mutableStateOf(false) }

    Box(Modifier.wrapContentSize().padding(end = 10.dp)) {
        Text(
            selected.name,
            Modifier
                .size(170.dp, 55.dp)
                .background(selected.color)
                .border(1.dp, Color.Gray)
        )
        Button(onClick = { showDropdown = !showDropdown }, content = { Text("^") },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(3.dp)
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

@Composable
fun LevelChoice(creatureVM: CreatureVM) {
    var popupControl by remember { mutableStateOf(false) }

    Text(
        creatureVM.creatureLevel.value.toString(),
        Modifier
            .size(180.dp, 55.dp)
            .background(Color.Gray)
            .border(1.dp, Color.Gray),
        textAlign = TextAlign.Left
    )
    Button(onClick = {popupControl = true}, content = {Text("^")})

    if (popupControl)
    Popup(focusable = true, onDismissRequest = {popupControl = false}) {
        Column(Modifier.padding(end = 30.dp,top = 60.dp).background(Color.Gray)) {
            Text(
                "Choose level of the creature!",
                Modifier
                    .padding(bottom = 5.dp)
                    .background(Color.Gray)
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(70.dp),
            ) {
                items(27) { index ->
                    Button(onClick = {
                        creatureVM.creatureLevel.value = index - 1
                        popupControl = false
                    }, Modifier.padding(5.dp)) {
                        Text((index - 1).toString())
                    }
                }
            }
        }
    }
}

@Composable
fun SecondaryTraits(creatureTraits: SnapshotStateList<String>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(160.dp)) {
        items(creatureTraits.size+1) { index ->
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