package views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import components.*
import data.*
import models.*

@Composable
fun SavingThrows_Component(creatureVM: CreatureVM) {
    Row(Modifier.padding(top = 8.dp)) {
        SavingThrow.values().forEach { savingThrow ->
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                Row {
                    NumericTextField(
                        value = creatureVM.creatureSavingThrows.modByStat(savingThrow),
                        onIntValueChange = { creatureVM.creatureSavingThrows.changeToMod(savingThrow, it) },
                        modifier = androidx.compose.ui.Modifier.width(85.dp),
                        textStyle = when (creatureVM.creatureSavingThrows.setups[savingThrow]) {
                            is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                            null, is StatSetup.Tier -> TextStyle.Default
                        },
                        explicitlySigned = true
                    ) { Text(text = savingThrow.name) }
                }
                TextDropdown(creatureVM.creatureSavingThrows.tierByStat(savingThrow), {
                    creatureVM.creatureSavingThrows.changeToStatTier(savingThrow, it)
                }, StatTier.values())
            }
        }
    }
}