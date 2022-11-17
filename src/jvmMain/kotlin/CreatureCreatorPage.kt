import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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

@Composable
fun creatureCreatorPage(applicationVM: ApplicationVM) {
    val creatureVM = remember { CreatureVM() }

    MaterialTheme {
        Column(
            Modifier.padding(15.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextField(
                    value = creatureVM.creatureName.value,
                    onValueChange = { creatureVM.creatureName.value = it },
                    label = { Text("Name") })
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = { applicationVM.page.value = Pages.HomePage },
                    content = { Text("x") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                )
            }

            Text("TRAITS")

            Row(Modifier.padding(vertical = 3.dp)) {
                DropdownWithColor(
                    creatureVM.creatureRarity.value,
                    { creatureVM.creatureRarity.value = it },
                    enumValues()
                )
                DropdownWithColor(
                    creatureVM.creatureAlignment.value,
                    { creatureVM.creatureAlignment.value = it },
                    enumValues()
                )
                DropdownWithColor(
                    creatureVM.creatureSize.value,
                    { creatureVM.creatureSize.value = it },
                    enumValues()
                )
                SecondaryTraits(creatureVM.creatureSecondaryTraits)
            }

            Text("ABILITY SCORES")

            AbilityScores(creatureVM)

            Text("SKILLS")

            ExperimentalSkillsGrid2(creatureVM)
        }
    }
}

@Composable
private fun AbilityScores(creatureVM: CreatureVM) {
    val pattern = remember { Regex("^\\d+\$") }

    Column {
        creatureVM.abilityScores.forEach { ability ->
            key(ability.name) {
                Row {
                    TextField(
                        value = TextFieldValue(
                            ability.score.toString(),
                            selection = TextRange(ability.score.toString().length)
                        ),
                        onValueChange = {
                            when {
                                it.text.isEmpty() -> ability.score = 0
                                it.text.matches(pattern) -> ability.score = it.text.toInt()
                                else -> ability.score = ability.score
                            }
                        },
                        label = { Text(ability.name) },
                    )

                    val mod = ability.modifier
                    Text(text = if (mod < 0) mod.toString() else "+$mod")
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun experimental_skills_grid(creatureVM: CreatureVM) {
    LazyVerticalGrid(cells = GridCells.Adaptive(160.dp)) {
        items(Skill.values()) { skill ->
            Text(skill.title)
            DropdownWithColor(creatureVM.proficiencies[skill] ?: Proficiency.Untrained, {
                creatureVM.proficiencies[skill] = it
            }, Proficiency.values())
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExperimentalSkillsGrid2(creatureVM: CreatureVM) {
    val items = Skill.values()

    LazyVerticalGrid(
        cells = GridCells.Adaptive(160.dp)
    ) {
        items(items.size) { index ->
            Column {
                Text(items[index].title, modifier = Modifier.absolutePadding(top = 5.dp))
                DropdownWithColor(creatureVM.proficiencies[items[index]] ?: Proficiency.Untrained, {
                    creatureVM.proficiencies[items[index]] = it
                }, Proficiency.values())
            }
        }
    }
}

@Composable
private fun skills_grid(creatureVM: CreatureVM) {
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

    Box(Modifier.wrapContentSize()) {
        Text(
            selected.name,
            Modifier.size(180.dp, 55.dp).background(selected.color).border(1.dp, Color.Gray),
        )
        Button(onClick = { showDropdown = !showDropdown }, content = { Text("^") },
            modifier = Modifier.align(Alignment.CenterEnd).padding(3.dp)
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
private fun SecondaryTraits(creatureTraits: SnapshotStateList<String>) {
    creatureTraits.forEachIndexed { index, value ->
        OutlinedTextField(
            value,
            { creatureTraits[index] = it },
            modifier = Modifier
                .width(180.dp)
                .then(
                    if (index != creatureTraits.size - 1)
                        Modifier.padding(horizontal = 5.dp)
                    else
                        Modifier.padding(start = 5.dp)
                )
        )
    }
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
