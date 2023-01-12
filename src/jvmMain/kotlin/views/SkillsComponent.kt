package views

import StatTier
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import components.*
import data.*
import models.*

@Composable
fun SkillsGrid(creatureVM: CreatureVM, modifier: Modifier = Modifier) { //TODO Fix selection and handle empty state
    val availableSkillsToPick by derivedStateOf { Skill.values().filter { !creatureVM.skills.contains(it) } }

    HorizontalFlow(modifier) {
        var i = 0
        while (i <= creatureVM.skills.size) {
            var index = i
            if (i < creatureVM.skills.size) {
                var skill = creatureVM.skills[index]
                Column(Modifier.padding(end = 5.dp)) {
                    Row {
                        DropdownWithColor(
                            selected = creatureVM.skills[index], onValueChanged = {
                                val oldSkill = creatureVM.skills[index]
                                creatureVM.skillModifiers.setups.remove(oldSkill)
                                creatureVM.skills[index] = it
                            },
                            values = availableSkillsToPick.toTypedArray()
                        )
                        Text(": ")
                        BasicTextField(
                            value = creatureVM.skillModifiers.modByStat(skill).toString(),
                            modifier = Modifier
                                .border(1.dp, Color.Gray)
                                .size(20.dp, 20.dp)
                                .wrapContentHeight()
                                .padding(2.dp)
                                .wrapContentWidth(),
                            textStyle = if (creatureVM.skillModifiers.setups[skill] is StatSetup.Modifier) TextStyle(
                                fontWeight = FontWeight.Bold
                            ) else TextStyle.Default,
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
            } else {
                Column(verticalArrangement = Arrangement.Top) {
                    Button(
                        onClick = {
                            creatureVM.skills.add(availableSkillsToPick[0])
                        },
                        Modifier.size(27.5f.dp),
                        contentPadding = PaddingValues(2.dp),
                    ) {
                        Text(text = "+", textAlign = TextAlign.Center, fontSize = 7.sp)
                    }
                    Button(
                        onClick = {
                            val skill = creatureVM.skills.removeLast()
                            creatureVM.skillModifiers.setups.remove(skill)
                        },
                        Modifier.size(27.5f.dp),
                        contentPadding = PaddingValues(2.dp),
                    ) {
                        Text("-", textAlign = TextAlign.Center, fontSize = 7.sp)
                    }
                }
            }
            i++
        }
    }
}