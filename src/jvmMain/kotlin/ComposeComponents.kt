import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.text.BasicTextField
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
    val pattern = remember { Regex("^[0-9]*\\.*-?[0-9]+\$") }

    Column {
        creatureVM.abilityScores.forEach { abilityInfo ->
            val (ability, abilityScoreInfo) = abilityInfo // read about Kotlin decompose
            key(ability.name) {
                Row {
                    //Ability score element
                    TextField(
                        value = TextFieldValue(
                            abilityScoreInfo.score.toString(),
                            selection = TextRange(abilityScoreInfo.score.toString().length)
                        ),
                        onValueChange = {
                            when {
                                it.text.isEmpty() -> abilityScoreInfo.score = 0
                                it.text.matches(pattern) -> abilityScoreInfo.score = it.text.toInt()
                                //Force recompose
                                else -> abilityScoreInfo.score = abilityScoreInfo.score
                            }
                        },
                        label = { Text(ability.name) },
                    )

                    val mod = abilityScoreInfo.modifier
                    Text(text = if (mod < 0) mod.toString() else "+$mod")
                }
            }
        }
    }
}

@Composable
fun Skills_Grid(creatureVM: CreatureVM) {
    val items = Skill.values()
    LazyVerticalGrid(columns = GridCells.Adaptive(120.dp),
       // modifier = Modifier.height(200.dp)
    ) {
        items(items.size) { index ->
            Column {
                val skill = items[index]
                Text(
                    skill.title + ":  " + creatureVM.skillModifiers[skill],
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
fun <T : DropdownItem> DropdownWithColor(
    selected: T, onValueChanged: (T) -> Unit, values: Array<T>, size: Dp = 120.dp, color: Color = Color.Transparent
) {
    var showDropdown by remember { mutableStateOf(false) }
    var backgroundColor = if (selected is ColorDropdownItem) selected.color else color

    Box(
        Modifier
            .padding(end = 10.dp)
            .height(30.dp),
            contentAlignment = Alignment.Center
    ) {
        Text(
            if (selected is SpecialNameDropdownItem) selected.altName else selected.name,
            Modifier
                .size(size, 55.dp)
                .background(backgroundColor)
                .border(1.dp, Color.Gray)
                .padding(3.dp)
                .wrapContentHeight()
        )
        Button(onClick = { showDropdown = !showDropdown }, content = { Text("^", textAlign = TextAlign.Center, fontSize = 7.sp) },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(3.dp)
                .size(27.5f.dp),
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

@Composable
fun LevelChoice(creatureVM: CreatureVM) {
    var popupControl by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center) {
        Text(
            creatureVM.creatureLevel.value.toString(),
            Modifier
                .size(65.dp, 55.dp)
                .background(Color.Gray)
                .border(1.dp, Color.Gray)
                .padding(3.dp)
                .wrapContentHeight(),
            textAlign = TextAlign.Left
        )
        Button(onClick = { popupControl = !popupControl }, content = { Text("^", textAlign = TextAlign.Center, fontSize = 7.sp) },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(3.dp)
                .size(27.5f.dp),
            contentPadding = PaddingValues(2.dp))
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
    LazyVerticalGrid(columns = GridCells.Adaptive(160.dp),
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

@Composable
fun Perception(creatureVM: CreatureVM) {
    Row {
        Column {
            Text(
                "Perception:   ${creatureVM.perceptionModifier}",
                modifier = Modifier.absolutePadding(top = 5.dp)
            )
            DropdownWithColor(
                selected = creatureVM.perceptionProficiency.value,
                onValueChanged = { creatureVM.perceptionProficiency.value = it },
                values = Proficiency.values(),
            )
        }
        Column {
            Text(" ", modifier = Modifier.absolutePadding(top = 5.dp))
            DropdownWithColor(
                selected = creatureVM.vision.value,
                onValueChanged = { creatureVM.vision.value = it },
                values = VisionType.values(),
                size = 170.dp,
            )
        }
    }
    Row(Modifier.padding(top = 10.dp)) {
       PerceptionTraits(creatureVM)
    }
}

@Composable
fun PerceptionTraits(creatureVM: CreatureVM) {
    val perceptionSecondaryTraits = creatureVM.perceptionSecondaryTraits
    val pattern = remember { Regex("^[0-9]*\\.*-?[0-9]+\$") }

    LazyVerticalGrid(columns = GridCells.Adaptive(270.dp),
        //modifier = Modifier.height(100.dp)
    ) {
        items(perceptionSecondaryTraits.size + 1) { index ->
            if (index < perceptionSecondaryTraits.size) {
                val trait = perceptionSecondaryTraits[index]
                Row {
                    OutlinedTextField(
                        trait.name,
                        { trait.name = it },
                        modifier = Modifier.width(200.dp)
                    )
                    Column {
                        DropdownWithColor(
                            selected = trait.sensePrecision,
                            onValueChanged = { trait.sensePrecision = it },
                            values = SensePrecision.values(),
                        )
                        Row {
                            Box(Modifier.border(1.dp, Color.Gray)) {
                            BasicTextField(
                                value = TextFieldValue(
                                    "${trait.range}",
                                    selection = TextRange(trait.range.toString().length + 2),
                                ),
                                onValueChange = {
                                    when {
                                        it.text.isEmpty() -> trait.range = 0
                                        it.text.matches(pattern) -> trait.range = it.text.toInt()
                                        //Force recompose
                                        else -> trait.range = trait.range
                                    }
                                },
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(26.dp)
                            )
                            }
                            Text(text = "ft")
                        }
                    }
                }
            }

            if (index == perceptionSecondaryTraits.size) {
                Column(verticalArrangement = Arrangement.Top) {
                    Button(
                        onClick = { perceptionSecondaryTraits.add(PerceptionSecondaryTrait()) },
                        Modifier.size(27.5f.dp),
                        contentPadding = PaddingValues(2.dp),
                    ) {
                        Text(text = "+", textAlign = TextAlign.Center, fontSize = 7.sp)
                    }
                    Button(
                        onClick = { perceptionSecondaryTraits.removeAt(perceptionSecondaryTraits.size - 1) },
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