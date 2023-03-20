package views

import data.Ability
import components.ItemDropdown
import data.abilityModifiersTable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import models.*

@Composable
fun AbilityGrid(creatureVM: CreatureVM, modifier: Modifier = Modifier) { //TODO Fix selection and handle empty state
    val keys = Ability.values()
    Column(modifier) {
        keys.forEach { key ->
            AbilityView(creatureVM, key)
        }
    }
}

@Composable
private fun AbilityView(creatureVM: CreatureVM, key: Ability) {
    Row {
        Box {
            var currentValue by remember(creatureVM.abilityModifiers.setups[key]) {
                mutableStateOf(creatureVM.abilityModifiers.modByStat(key).toString())
            }
            var errorState by remember {
                mutableStateOf(false)
            }

            TextField(
                value = currentValue,
                onValueChange = {
                    currentValue = it
                    when (val intValue = it.toIntOrNull()) {
                        null -> {
                            errorState = true
                        }

                        else -> {
                            errorState = false
                            creatureVM.abilityModifiers.changeToMod(key, intValue)
                        }
                    }
                },
                textStyle = if (creatureVM.abilityModifiers.setups[key] is StatSetup.Modifier) TextStyle(
                    fontWeight = FontWeight.Bold
                ) else TextStyle.Default,
                label = { Text(text = key.name) }
            )
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                ItemDropdown(creatureVM.abilityModifiers.tierByStat(key), {
                    creatureVM.abilityModifiers.changeToStatTier(key, it)
                }, abilityModifiersTable[0]!!.keys.toTypedArray())
            }
        }
    }
}