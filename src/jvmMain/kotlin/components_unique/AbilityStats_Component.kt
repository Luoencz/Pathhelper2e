package components_unique

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import components_general.*
import data.*
import models.*

@Composable
fun AbilitiesStats_Component(
    creatureVM: CreatureVM,
    modifier: Modifier = Modifier
) { //TODO Fix selection and handle empty state
    val keys = Ability.values()
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        keys.forEach { key ->
            var label = when (key) {
                Ability.Strength -> "STR"
                Ability.Dexterity -> "DEX"
                Ability.Constitution -> "CON"
                Ability.Intelligence -> "INT"
                Ability.Wisdom -> "WIS"
                Ability.Charisma -> "CHA"
            }
            TierStatView(modifier, creatureVM.abilityModifiers, key, label, true)
        }
    }
}