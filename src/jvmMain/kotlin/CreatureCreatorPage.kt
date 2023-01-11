import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun creatureCreatorPage(applicationVM: ApplicationVM) {
    val creatureVM = remember { CreatureVM() }

    MaterialTheme {
        Column(
            Modifier
                .padding(15.dp)
            //.verticalScroll(rememberScrollState())
        ) {
            Row(Modifier.fillMaxWidth()) {
                //Creature Name
                TextField(
                    value = creatureVM.creatureName,
                    onValueChange = { creatureVM.creatureName = it },
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
                    enumValues(),
                    size = 80.dp
                )
                DropdownWithColor(
                    creatureVM.creatureSize.value,
                    { creatureVM.creatureSize.value = it },
                    enumValues(),
                    size = 130.dp
                )

            }

            //Secondary Traits
            SecondaryTraits(creatureVM.creatureSecondaryTraits)

            //Ability Scores and Perception
            Row(Modifier.padding(top = 10.dp)) {
                Column {
                    Text("ABILITY SCORES")
                    AbilityScores(creatureVM)
                }

                Column(Modifier.padding(start = 40.dp)) {
                    Text("PERCEPTION")
                    PerceptionMod(creatureVM)

                    Text("LANGUAGES", modifier = Modifier.padding(top = 20.dp))
                    SecondaryTraits(creatureTraits = creatureVM.creatureLanguages)
                }
            }

            Text("SKILLS", Modifier.padding(top = 10.dp))

            //Skills Grid
            SkillsGrid(creatureVM)
        }
    }
}

