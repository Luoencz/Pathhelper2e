package pages

import BasicNamedObject
import Pages
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.*
import components_general.*
import models.*
import components_unique.*

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

            Row(verticalAlignment = Alignment.CenterVertically) {

                //Creature Name
                Name_Component(creatureVM)

                //Level Choice
                CL_Component(creatureVM = creatureVM)

                Column(
                    Modifier.padding(start = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Rarity")
                    TextDropdown(
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
                    TextDropdown(
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

                    TextDropdown(
                        creatureVM.creatureSize.value,
                        { creatureVM.creatureSize.value = it },
                        enumValues(),
                        size = Modifier.size(100.dp, 25.dp)
                    )
                }

                //Secondary Traits
                NamedList(
                    modifier = Modifier.padding(start = 15.dp),
                    traitsList = creatureVM.creatureSecondaryTraits,
                    lambdaConstructor = { BasicNamedObject(it) }
                )
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
                        Senses_Component(creatureVM)
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
                            onIntValueChange = { creatureVM.creatureSpeed.value = it }) {}
                    }
                }
                Column {
                    Text("Resistances & Weaknesses")
                    Row(Modifier.padding(top = 6.dp)) {
                        ResistancesAndWeaknesses_Component(creatureVM = creatureVM)
                    }
                }
            }

            Column {
                Text("Languages")
                Row(Modifier.padding(top = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Languages_Component(creatureVM)
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Skills")
                Skills_Component(creatureVM = creatureVM)
                Lore_Component(creatureVM = creatureVM)
            }

            dragAndDropGrid(creatureVM.CreatureCharacteristics) { characteristicCard(it) }
        }
    }
}
