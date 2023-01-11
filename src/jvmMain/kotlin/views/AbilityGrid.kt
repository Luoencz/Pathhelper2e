package views

import data.Ability
import models.CreatureVM
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun AbilityGrid(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    val pattern = remember { Regex("^[0-9]*\\.*-?[0-9]+\$") }
    val items = Ability.values()

    Column(modifier) {
        items.forEach { ability ->
            Row {
                Box {
                    TextField(
                        value = TextFieldValue(
                            creatureVM.abilityModifiers.modByStat(ability).toString(),
                            selection = TextRange(creatureVM.abilityModifiers.modByStat(ability).toString().length)
                        ),
                        onValueChange = {
                            when {
                                it.text.isEmpty() -> creatureVM.abilityModifiers.changeToMod(ability, 0)
                                it.text.matches(pattern) -> creatureVM.abilityModifiers.changeToMod(
                                    ability,
                                    it.text.toInt()
                                )
                                //else -> creatureVM.abilityModifiers.changeToMod(ability,creatureVM.abilityModifiers.modByStat(ability))
                            }
                        },
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