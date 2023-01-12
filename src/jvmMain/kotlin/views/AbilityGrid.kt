package views

import data.Ability
import components.DropdownWithColor
import abilityModifiersTable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.TextFieldValue
import models.*

@Composable
fun AbilityGrid(creatureVM: CreatureVM, modifier: Modifier = Modifier) { //TODO Fix selection and handle empty state
    val items = Ability.values()

    Column(modifier) {
        items.forEach { ability ->
            Row {
                Box {
                    TextField(
                        value = creatureVM.abilityModifiers.modByStat(ability).toString(),
                        onValueChange = {
                            when {
                                it.isEmpty() -> creatureVM.abilityModifiers.changeToMod(ability, 0)
                                it.toIntOrNull() != null -> creatureVM.abilityModifiers.changeToMod(
                                    ability,
                                    it.toInt()
                                )
                            }
                        },
                        textStyle = if (creatureVM.abilityModifiers.setups[ability] is StatSetup.Modifier) TextStyle(fontWeight = FontWeight.Bold) else TextStyle.Default,
                        label = { Text(text = ability.name) }
                    )
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        DropdownWithColor(creatureVM.abilityModifiers.tierByStat(ability), {
                            creatureVM.abilityModifiers.changeToStatTier(ability, it)
                        }, abilityModifiersTable[0]!!.keys.toTypedArray())
                    }
                }
            }
        }
    }
}