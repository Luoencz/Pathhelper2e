package pages

import Pages
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.google.relay.compose.RelayText
import components_general.*
import models.*
import components_unique.*
import components_unique.Identity
import data.*

@Composable
fun creatureMainStats(applicationVM: ApplicationVM) {
    val creatureVM = remember { applicationVM.creatureVM }

    MaterialTheme {
        Column(
            Modifier
                .padding(15.dp)
            //.verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.spacedBy(10.dp)
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

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Column {
                    Identity(creatureVM)
                }
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Row {
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = AnnotatedString("Rarity"),
                                style = TitleTextStyle
                            )
                            TextDropdown(
                                textStyle = TextStyle(fontSize = 17.sp),
                                selected = creatureVM.creatureRarity.value,
                                onValueChanged = { creatureVM.creatureRarity.value = it },
                                values = enumValues(),
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = AnnotatedString("Alignment"),
                                style = TitleTextStyle
                            )
                            TextDropdown(
                                textStyle = TextStyle(fontSize = 17.sp),
                                selected = creatureVM.creatureAlignment.value,
                                onValueChanged = { creatureVM.creatureAlignment.value = it },
                                values = enumValues(),
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = AnnotatedString("Size"),
                                style = TitleTextStyle
                            )
                            TextDropdown(
                                textStyle = TextStyle(fontSize = 17.sp),
                                selected = creatureVM.creatureSize.value,
                                onValueChanged = { creatureVM.creatureSize.value = it },
                                values = enumValues(),
                            )
                        }
                    }
                    Spacer(modifier = Modifier)
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = AnnotatedString("Ability Modifiers"),
                                style = TitleTextStyle
                            )
                            AbilitiesStats_Component(creatureVM)
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = AnnotatedString("Perception"),
                                style = TitleTextStyle
                            )
                            Perception_Component(creatureVM)
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp), modifier = Modifier.padding(top = 2.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = AnnotatedString("AC, HP & Saving Throws"),
                                style = TitleTextStyle
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                                AC_Component(creatureVM = creatureVM)
                                HP_Component(creatureVM = creatureVM)
                                SavingThrows_Component(creatureVM = creatureVM)
                            }
                        }
                        Spacer(modifier = Modifier.width(60.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                           Text(
                                text = AnnotatedString("Speed"),
                               style = TitleTextStyle
                            )
                            NumericTextField(
                                value = creatureVM.creatureSpeed.value,
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(35.dp),
                                onIntValueChange = { creatureVM.creatureSpeed.value = it })
                        }
                    }
                }
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            dragAndDropGrid(creatureVM.CreatureCharacteristics) { characteristicCard(it) }
        }
        }
    }