import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.*

@Composable
fun creatureCreatorPage(applicationVM: ApplicationVM) {
    val creatureVM = remember { CreatureVM() }
    val pattern = remember { Regex("^[0-9]*\\.*-?[0-9]+\$") }

    MaterialTheme {
        Column(
            Modifier.padding(15.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextField(
                    value = creatureVM.creatureName.value,
                    onValueChange = { creatureVM.creatureName.value = it },
                    label = { Text("Name") })
                /* TextField(
                     value = TextFieldValue(
                         creatureVM.creatureLevel.value.toString(),
                         selection = TextRange(creatureVM.creatureLevel.value.toString().length)
                     ),
                     onValueChange = {
                         if (creatureVM.creatureLevel.value < -1) {
                             creatureVM.creatureLevel.value = 0
                         }
                         when {
                             it.text.isEmpty() -> creatureVM.creatureLevel.value = -1
                             it.text.matches(pattern) -> creatureVM.creatureLevel.value = it.text.toInt()
                             else -> creatureVM.creatureLevel.value = creatureVM.creatureLevel.value
                         }
                         println(creatureVM.creatureLevel.value)
                     },
                    label = { Text("Level") }
                ) */
                LevelChoice(creatureVM = creatureVM)
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

            }
            SecondaryTraits(creatureVM.creatureSecondaryTraits)

            Text("ABILITY SCORES")

            AbilityScores(creatureVM)

            Text("SKILLS")

            ExperimentalSkillsGrid2(creatureVM)
        }
    }
}

