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
                ) { Text(text = "Trait") }
            }

            Row(Modifier.padding(top = 6.dp)) {
                Column {
                    Text("ABILITY MODIFIERS")
                    AbilitiesStats_Component(creatureVM, Modifier.padding(top = 6.dp))
                }
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                Column {
                    Text("DEFENSE")
                    Row(Modifier.padding(top = 6.dp)) {
                        AC_Component(creatureVM = creatureVM, Modifier.padding(end = 3.dp))
                        HP_Component(creatureVM = creatureVM, Modifier.padding(end = 6.dp))
                        SavingThrows_Component(creatureVM = creatureVM)
                    }
                }
            }

            Row(Modifier.padding(top = 6.dp)) {
                val localDensity = LocalDensity.current
                var columnHeight by remember { mutableStateOf(0.dp) }
                Column {
                    Text("PERCEPTION")
                    Row(
                        Modifier
                            .padding(top = 6.dp)
                            .onGloballyPositioned { layoutCoordinates ->
                                columnHeight = with(localDensity) { layoutCoordinates.size.height.toDp() }
                            }, verticalAlignment = Alignment.CenterVertically) {
                        Perception_Component(creatureVM)
                        Senses_Component(creatureVM)
                    }
                }
                Column(Modifier.padding(start = 10.dp)) {
                    Text("LANGUAGES")
                    Row(
                        Modifier
                            .padding(top = 6.dp)
                            .height(columnHeight), verticalAlignment = Alignment.CenterVertically) {
                        Languages_Component(creatureVM)
                    }
                }
            }

            Column(Modifier.padding(top = 6.dp)) {
                Text("SKILLS")
                Skills_Component(creatureVM,Modifier.padding(top = 6.dp))
            }

        }
    }
}
