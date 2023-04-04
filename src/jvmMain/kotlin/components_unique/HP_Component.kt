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
fun HP_Component(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        NumericTextField(
            value = creatureVM.creatureHP.modByStat(HP.HP),
            onIntValueChange = { creatureVM.creatureHP.changeToMod(HP.HP, it) },
            modifier = Modifier.width(85.dp),
            textStyle = when (creatureVM.creatureHP.setups[HP.HP]) {
                is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                null, is StatSetup.Tier -> TextStyle.Default
            },
        ) { Text(text = "Hit Points") }
        TextDropdown(creatureVM.creatureHP.tierByStat(HP.HP), {
            creatureVM.creatureHP.changeToStatTier(HP.HP, it)
        }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low, StatTier.Terrible))
    }
}