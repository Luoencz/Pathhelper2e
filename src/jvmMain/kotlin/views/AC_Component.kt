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
fun AC_Component(creatureVM: CreatureVM) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
        NumericTextField(
            value = creatureVM.creatureAC.modByStat(AC.AC),
            onIntValueChange = { creatureVM.creatureAC.changeToMod(AC.AC, it) },
            modifier = androidx.compose.ui.Modifier.width(85.dp),
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