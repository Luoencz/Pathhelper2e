import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
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
                 TextField(
                    value = creatureVM.creatureLevel.value.toString(),
                    onValueChange = {creatureVM.creatureLevel.value = it.toInt()},
                    label = { Text("Level") }
                )
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

