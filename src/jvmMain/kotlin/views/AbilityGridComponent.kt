package views

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import components.*
import data.*
import models.*

@Composable
fun AbilityGrid(creatureVM: CreatureVM, modifier: Modifier = Modifier) { //TODO Fix selection and handle empty state
    val keys = Ability.values()
    Row(modifier) {
        keys.forEach { key ->
            AbilityView(creatureVM, key)
        }
    }
}

@Composable
private fun AbilityView(creatureVM: CreatureVM, key: Ability) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(1.5.dp)
    ) {
        NumericTextField(
            value = creatureVM.abilityModifiers.modByStat(key),
            label = key.name,
            textStyle = when (creatureVM.abilityModifiers.setups[key]) {
                is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                null, is StatSetup.Tier -> TextStyle.Default
            },
            modifier = Modifier.width(101.9.dp),
            onIntValueChange = { creatureVM.abilityModifiers.changeToMod(key, it) },
            explicitlySigned = true
        )
        ItemDropdown(creatureVM.abilityModifiers.tierByStat(key), {
            creatureVM.abilityModifiers.changeToStatTier(key, it)
        }, abilityModifiersTable[0]!!.keys.toTypedArray())
    }
}