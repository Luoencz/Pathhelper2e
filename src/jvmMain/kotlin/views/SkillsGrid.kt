package views

import models.CreatureVM
import data.Skill
import StatTier
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import components.*

@Composable
fun SkillsGrid(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    val pattern = remember { Regex("^[0-9]*\\.*-?[0-9]+\$") }
    val items = Skill.values()

    HorizontalFlow(modifier) {
        items.forEach { skill ->
            Column(Modifier.padding(end = 5.dp)) {
                val mod = creatureVM.skillModifiers.modByStat(skill)
                Row {
                    Text(skill.name + ": ")
                    BasicTextField(
                        TextFieldValue(
                            creatureVM.skillModifiers.modByStat(skill).toString(),
                            selection = TextRange(mod.toString().length),
                        ),
                        modifier = Modifier.border(1.dp, Color.Gray).size(20.dp, 20.dp).wrapContentHeight().padding(2.dp).wrapContentWidth(),
                        onValueChange = {
                            when {
                                it.text.isEmpty() -> creatureVM.skillModifiers.changeToMod(skill, 0)
                                it.text.toIntOrNull() != null -> creatureVM.skillModifiers.changeToMod(
                                    skill,
                                    it.text.toInt()
                                )
                                //else -> creatureVM.abilityModifiers.changeToMod(ability,creatureVM.abilityModifiers.modByStat(ability))
                            }
                        }
                    )
                }
                DropdownWithColor(creatureVM.skillModifiers.tierByStat(skill), {
                    creatureVM.skillModifiers.changeToStatTier(skill, it)
                }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low))
            }
        }
    }
}