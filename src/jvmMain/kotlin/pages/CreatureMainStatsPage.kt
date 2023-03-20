package pages

import Pages
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import components.*
import models.*
import views.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun creatureMainStats(applicationVM: ApplicationVM) {
    val creatureVM = remember { applicationVM.creatureVM }

    MaterialTheme {
        Column(
            Modifier
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, Color.Black, RoundedCornerShape(20))
                    .padding(10.dp, 0.dp)) {
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
                Row(
                    Modifier
                        .border(width = 1.dp, Color.Black, RoundedCornerShape(20))
                        .padding(10.dp, 0.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    //Creature Name
                    OutlinedTextField(
                        value = creatureVM.creatureName,
                        onValueChange = { creatureVM.creatureName = it },
                        textStyle = TextStyle.Default.copy(fontSize = 14.sp),
                        modifier = Modifier
                            .height(50.dp)
                            .widthIn(5.dp, Dp.Infinity),
                        singleLine = true,
                        placeholder = { Text(text = "NAME") }
                    )

                    //Level Choice
                    LevelChoice(creatureVM = creatureVM)
                    Text(text = ": Level")

                    Column(
                        Modifier.padding(6.dp, 0.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Rarity")
                        ItemDropdown(
                            creatureVM.creatureRarity.value,
                            { creatureVM.creatureRarity.value = it },
                            enumValues(),
                        )
                    }

                    Column(
                        Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Alignment")
                        ItemDropdown(
                            creatureVM.creatureAlignment.value,
                            { creatureVM.creatureAlignment.value = it },
                            enumValues(),
                            size = 80.dp
                        )
                    }

                    Column(
                        Modifier.padding(3.dp, 0.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Size")

                        ItemDropdown(
                            creatureVM.creatureSize.value,
                            { creatureVM.creatureSize.value = it },
                            enumValues(),
                            size = 130.dp
                        )
                    }
                }
                //SecondaryTraits(creatureVM.creatureSecondaryTraits)
                SecondaryTraitsComponentV2(creatureVM = creatureVM)
            }

            Row {
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

