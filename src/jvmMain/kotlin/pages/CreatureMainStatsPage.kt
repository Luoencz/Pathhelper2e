package pages

import Pages
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.google.relay.compose.BoxScopeInstance.rowWeight
import com.google.relay.compose.RelayText
import components_general.*
import models.*
import components_unique.*
import relay_components.Identity

@Composable
fun creatureMainStats(applicationVM: ApplicationVM) {
    val creatureVM = remember { applicationVM.creatureVM }

    MaterialTheme {
        Column(
            Modifier
                .padding(15.dp)
            //.verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.spacedBy(6.dp)
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

            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Column {
                    Identity(creatureVM)
                }
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Row {
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            RelayText(
                                color = Color(
                                    alpha = 255,
                                    red = 96,
                                    green = 27,
                                    blue = 21
                                ),
                                height = 1.171875.em,
                                textAlign = TextAlign.Left,
                                maxLines = -1,
                                modifier = Modifier.requiredHeight(25.0.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically).padding(horizontal = 4.dp)
                            ) { m, max, over, st ->
                                BasicText(
                                    "Rarity",
                                    modifier = m,
                                    maxLines = max,
                                    overflow = over,
                                    style = st,
                                )
                            }
                            TextDropdown(
                                creatureVM.creatureRarity.value,
                                { creatureVM.creatureRarity.value = it },
                                enumValues(),
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            RelayText(
                                color = Color(
                                    alpha = 255,
                                    red = 96,
                                    green = 27,
                                    blue = 21
                                ),
                                height = 1.171875.em,
                                textAlign = TextAlign.Left,
                                maxLines = -1,
                                modifier = Modifier.requiredHeight(25.0.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically).padding(horizontal = 4.dp)
                            ) { m, max, over, st ->
                                BasicText(
                                    "Alignment",
                                    modifier = m,
                                    maxLines = max,
                                    overflow = over,
                                    style = st,
                                )
                            }
                            TextDropdown(
                                creatureVM.creatureAlignment.value,
                                { creatureVM.creatureAlignment.value = it },
                                enumValues(),
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            RelayText(
                                color = Color(
                                    alpha = 255,
                                    red = 96,
                                    green = 27,
                                    blue = 21
                                ),
                                height = 1.171875.em,
                                textAlign = TextAlign.Left,
                                maxLines = -1,
                                modifier = Modifier.requiredHeight(25.0.dp)
                                    .wrapContentHeight(align = Alignment.CenterVertically).padding(horizontal = 4.dp)
                            ) { m, max, over, st ->
                                BasicText(
                                    "Size",
                                    modifier = m,
                                    maxLines = max,
                                    overflow = over,
                                    style = st,
                                )
                            }

                            TextDropdown(
                                creatureVM.creatureSize.value,
                                { creatureVM.creatureSize.value = it },
                                enumValues(),
                            )
                        }
                    }
                    Row {
                        Column {
                            Text("Ability Modifiers")
                            AbilitiesStats_Component(creatureVM, Modifier.padding(top = 6.dp))
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                        Column {
                            Text("Perception")
                            Row(Modifier.padding(top = 6.dp), horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                                Perception_Component(creatureVM)
                            }
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Column {
                            Text("AC, HP & Saving Throws")
                            Row(Modifier.padding(top = 6.dp)) {
                                AC_Component(creatureVM = creatureVM, Modifier.padding(end = 3.dp))
                                HP_Component(creatureVM = creatureVM, Modifier.padding(end = 6.dp))
                                SavingThrows_Component(creatureVM = creatureVM)
                            }
                        }
                        Column {
                            Text("Speed")
                            Row(Modifier.padding(top = 6.dp)) {
                                NumericTextField(
                                    value = creatureVM.creatureSpeed.value,
                                    modifier = Modifier.width(50.dp),
                                    onIntValueChange = { creatureVM.creatureSpeed.value = it })
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            dragAndDropGrid(creatureVM.CreatureCharacteristics) { characteristicCard(it) }
        }
    }
}
