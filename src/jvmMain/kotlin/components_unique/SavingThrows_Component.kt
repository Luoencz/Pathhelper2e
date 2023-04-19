package components_unique

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import components_general.*
import data.*
import models.*

@Composable
fun SavingThrows_Component(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    Row(modifier) {
        SavingThrow.values().forEach { savingThrow ->
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                    NumericTextField(
                        value = creatureVM.creatureSavingThrows.modByStat(savingThrow),
                        onIntValueChange = { creatureVM.creatureSavingThrows.changeToMod(savingThrow, it) },
                        modifier = Modifier.width(85.dp),
                        textStyle = when (creatureVM.creatureSavingThrows.setups[savingThrow]) {
                            is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                            null, is StatSetup.Tier -> TextStyle.Default
                        },
                        explicitlySigned = true,
                        label = savingThrow.name
                    )
                TextDropdown(creatureVM.creatureSavingThrows.tierByStat(savingThrow), {
                    creatureVM.creatureSavingThrows.changeToStatTier(savingThrow, it)
                }, StatTier.values())
            }
        }
    }
}