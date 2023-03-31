package pages

import GeneralTrait
import Pages
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.input.*
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
                    .padding(10.dp, 0.dp)
            ) {
                Button(
                    onClick = { },
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

            Row(Modifier.padding(top = 5.dp), verticalAlignment = Alignment.CenterVertically) {

                //Creature Name
                val interactionSource = remember { MutableInteractionSource() }
                BasicTextField(
                    value = creatureVM.creatureName,
                    onValueChange = { creatureVM.creatureName = it },
                    interactionSource = interactionSource,
                    enabled = true,
                    singleLine = true,
                    modifier = Modifier
                        .widthIn(10.dp, Dp.Infinity)
                ) {
                    TextFieldDefaults.OutlinedTextFieldDecorationBox(
                        value = creatureVM.creatureName,
                        visualTransformation = VisualTransformation.None,
                        innerTextField = it,
                        singleLine = true,
                        enabled = true,
                        label = { Text(text = "Name") },
                        interactionSource = interactionSource,
                        // keep vertical paddings but change the horizontal
                        contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                            start = 8.dp, end = 8.dp
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors()
                    )
                }

                //Level Choice
                LevelChoice(creatureVM = creatureVM)
                Text(text = ": Level")

                Column(
                    Modifier.padding(start = 6.dp),
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
                    Modifier.padding(start = 3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Alignment")
                    ItemDropdown(
                        creatureVM.creatureAlignment.value,
                        { creatureVM.creatureAlignment.value = it },
                        enumValues(),
                    )
                }

                Column(
                    Modifier.padding(start = 3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Size")

                    ItemDropdown(
                        creatureVM.creatureSize.value,
                        { creatureVM.creatureSize.value = it },
                        enumValues(),
                    )
                }

                //Secondary Traits
                NamedList(
                    modifier = Modifier.padding(start = 15.dp),
                    traitsList = creatureVM.creatureSecondaryTraits,
                    lambdaConstructor = {GeneralTrait(it)}
                ) { androidx.compose.material.Text(text = "Trait") }
            }

            Row {
                Column(Modifier.weight(0.5f)) {
                    Text("ABILITY MODIFIERS")
                    AbilitiesStats(creatureVM, Modifier.padding(top = 6.dp))
                }
            }


            //data.Ability Scores and Perception
            Row(Modifier.padding(top = 10.dp)) {
                Column {
                    Text("DEFENSE STATS")
                    DefenseStats(creatureVM = creatureVM)
                }

                Column(Modifier.padding(start = 40.dp)) {
                    Text("PERCEPTION")
                    PerceptionStats(creatureVM, Modifier.padding(top = 8.dp))

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

