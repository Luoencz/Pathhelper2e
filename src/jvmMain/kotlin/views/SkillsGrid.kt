package views

import data.Skill
import StatTier
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import components.*
import models.*

@Composable
fun SkillsGrid(creatureVM: CreatureVM, modifier: Modifier = Modifier) { //TODO Fix selection and handle empty state
    val items = Skill.values()

    HorizontalFlow(modifier) {
        items.forEach { skill ->
            Column(Modifier.padding(end = 5.dp)) {
                val mod = creatureVM.skillModifiers.modByStat(skill)
                Row {
                    Text(skill.name + ": ")
                    BasicTextField(
                        value = creatureVM.skillModifiers.modByStat(skill).toString(),
                        modifier = Modifier.border(1.dp, Color.Gray).size(20.dp, 20.dp).wrapContentHeight().padding(2.dp).wrapContentWidth(),
                        textStyle = if (creatureVM.skillModifiers.setups[skill] is StatSetup.Modifier) TextStyle(fontWeight = FontWeight.Bold) else TextStyle.Default,
                        onValueChange = {
                            when {
                                it.isEmpty() -> creatureVM.skillModifiers.changeToMod(skill, 0)
                                it.toIntOrNull() != null -> creatureVM.skillModifiers.changeToMod(
                                    skill,
                                    it.toInt()
                                )
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