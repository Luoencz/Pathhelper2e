package views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import components.*
import data.*
import models.*

@Composable
fun Perception_Component(creatureVM: CreatureVM) {
    Column {
        NumericTextField(
            value = creatureVM.creaturePerception.modByStat(Perception.Perception),
            onIntValueChange = { creatureVM.creaturePerception.changeToMod(Perception.Perception, it) },
            modifier = androidx.compose.ui.Modifier.width(85.dp),
            textStyle = when (creatureVM.creaturePerception.setups[Perception.Perception]) {
                is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                null, is StatSetup.Tier -> TextStyle.Default
            },
            explicitlySigned = true
        ) { Text(text = "Perception") }
        TextDropdown(creatureVM.creaturePerception.tierByStat(Perception.Perception), {
            creatureVM.creaturePerception.changeToStatTier(Perception.Perception, it)
        }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low, StatTier.Terrible))
    }
}