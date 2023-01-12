package pages

import Pages
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import components.*
import models.*
import views.*

@Composable
fun creatureMainStats(applicationVM: ApplicationVM) {
    val creatureVM = remember { applicationVM.creatureVM }

    MaterialTheme {
        Column(
            Modifier
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(Modifier.fillMaxWidth()) {
                Button(
                    onClick = {  },
                    content = { Text(text = "Main Stats") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(25, 77, 37))
                )
                Button(
                    onClick = { applicationVM.page.value = Pages.CreatureAbilitiesAndActionsPage },
                    content = { Text(text = "Passive abilities and Actions") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                )
                //Spacer moves exit button to the right end of the screen
                Spacer(Modifier.weight(1f))

                //Exit Button (comebacks to the Home Page)
                Button(
                    onClick = { applicationVM.page.value = Pages.HomePage },
                    content = { Text("x") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                )
            }
            Row {
                //Creature Name
                TextField(
                    value = creatureVM.creatureName,
                    onValueChange = { creatureVM.creatureName = it },
                    label = { Text("Name") })

                //Level Choice
                LevelChoice(creatureVM = creatureVM)
            }

            Row {
                Column(Modifier.weight(0.5f)) {
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
                }

                Column(Modifier.weight(0.5f)) {
                    DefenseStats(creatureVM = creatureVM)
                }
            }


            //data.Ability Scores and Perception
            Row(Modifier.padding(top = 10.dp)) {
                Column {
                    Text("ABILITY MODIFIERS")
                    AbilityGrid(creatureVM)
                }

                Column(Modifier.padding(start = 40.dp)) {
                    Text("PERCEPTION")
                    PerceptionMod(creatureVM)

                    Text("LANGUAGES", modifier = Modifier.padding(top = 20.dp))
                    SecondaryTraits(creatureTraits = creatureVM.creatureLanguages)
                }
            }

            Column {
                Text("SKILLS", Modifier.padding(top = 10.dp))

                //Skills Grid
                SkillsGrid(creatureVM)
            }
        }
    }
}

