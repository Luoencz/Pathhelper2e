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
import androidx.compose.ui.unit.*
import components_general.*
import components_unique.*
import data.*
import models.*

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
                                selected = creatureVM.rarity.value,
                                onValueChanged = { creatureVM.rarity.value = it },
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
                                selected = creatureVM.alignment.value,
                                onValueChanged = { creatureVM.alignment.value = it },
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
                                selected = creatureVM.size.value,
                                onValueChanged = { creatureVM.size.value = it },
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
                            TierStatView(
                                modifier = Modifier,
                                creatureVM.perceptionCharacteristic.stat.value
                            )
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp), modifier = Modifier.padding(top = 2.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = AnnotatedString("AC, HP & Saving Throws"),
                                style = TitleTextStyle
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                                TierStatView(Modifier, creatureVM.defenseCharacteristics[0].stat.value, "HP")
                                TierStatView(Modifier, creatureVM.defenseCharacteristics[1].stat.value, "AC")
                                Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                                    creatureVM.defenseCharacteristics.subList(2,5).forEach { savingThrow ->
                                        TierStatView(
                                            modifier = Modifier,
                                            savingThrow.stat.value,
                                            label = when (savingThrow.name.value) {
                                                "Reflex" -> "Ref"
                                                "Fortitude" -> "Fort"
                                                else -> savingThrow.name.value
                                            },
                                            true
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(60.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = AnnotatedString("Speed"),
                                style = TitleTextStyle
                            )
                            NumericTextField(
                                value = creatureVM.speed.value,
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(35.dp),
                                onIntValueChange = { creatureVM.speed.value = it }
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            dragAndDropGrid(creatureVM.creatureCharacteristics) { characteristicCard(it.value) }
        }
    }
}