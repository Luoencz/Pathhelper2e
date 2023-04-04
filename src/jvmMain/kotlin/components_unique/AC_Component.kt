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
fun AC_Component(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        NumericTextField(
            value = creatureVM.creatureAC.modByStat(AC.AC),
            onIntValueChange = { creatureVM.creatureAC.changeToMod(AC.AC, it) },
            modifier = Modifier.width(85.dp),
            textStyle = when (creatureVM.creatureAC.setups[AC.AC]) {
                is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                null, is StatSetup.Tier -> TextStyle.Default
            },
            explicitlySigned = false
        ) { Text(text = "Armor Class") }
        TextDropdown(creatureVM.creatureAC.tierByStat(AC.AC), {
            creatureVM.creatureAC.changeToStatTier(AC.AC, it)
        }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low))
    }
}