package components_unique

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import components_general.*
import models.*

@Composable
fun AbilitiesStats_Component(
    creatureVM: CreatureVM,
    modifier: Modifier = Modifier
) { //TODO Fix selection and handle empty state
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        creatureVM.abilityCharacteristics.forEach {
            val label = when (it.name.value) {
                "Strength" -> "STR"
                "Dexterity" -> "DEX"
                "Consitution" -> "CON"
                "Intelligence" -> "INT"
                "Wishom" -> "WIS"
                "Charisma" -> "CHA"
                else -> it.name.value
            }
            TierStatView(modifier, it.stat.value, label, true)
        }
    }
}