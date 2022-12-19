import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
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
                //Creature Name
                TextField(
                    value = creatureVM.creatureName.value,
                    onValueChange = { creatureVM.creatureName.value = it },
                    label = { Text("Name") })

                //Level Choice
                LevelChoice(creatureVM = creatureVM)

                //Spacer moves exit button to the right end of the screen
                Spacer(Modifier.weight(1f))

                //Exit Button (comebacks to the Home Page)
                Button(
                    onClick = { applicationVM.page.value = Pages.HomePage },
                    content = { Text("x") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                )
            }


            Text("TRAITS")

            //Primary traits
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

            //Secondary Traits
            SecondaryTraits(creatureVM.creatureSecondaryTraits)

            //Ability Scores and Perception
            Row {
                Column {
                    Text("ABILITY SCORES")
                    AbilityScores(creatureVM)
                }

                Column(Modifier.padding(start = 40.dp)) {
                    Text("PERCEPTION")
                    Perception(creatureVM)
                }
            }

            Text("SKILLS")

            //Skills Grid
            Skills_Grid(creatureVM)
        }
    }
}

