package components_unique

import BasicNamedObject
import data.StatTier
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import components_general.*
import data.*
import models.*

@Composable
fun Skills_Component(creatureVM: CreatureVM, modifier: Modifier = Modifier) { //TODO Fix selection and handle empty state 
    HorizontalFlow(modifier = modifier) {
        creatureVM.skillModifiers.setups.keys.forEach { skill ->
            SkillView(skill = skill, creatureVM = creatureVM)
            Spacer(modifier = Modifier.padding(horizontal = 1.dp))
        }
    }
}

@Composable
fun Lore_Component(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    NamedList(modifier = modifier, traitsList = creatureVM.knownLore, lambdaConstructor = ::BasicNamedObject, label = { Text(
        text = "New Lore"
    )}, content = { key ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NamedBubble(trait = key, modifier = Modifier.padding(end = 2.dp), traitsList = creatureVM.knownLore) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(
                        text = it.name,
                        maxLines = 1,
                        modifier = Modifier
                            .widthIn(1.dp, Dp.Infinity)
                            .padding(start = 3.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(10))
                            .padding(5.dp)
                    )
                        NumericTextField(
                            value = creatureVM.loreModifiers.modByStat(key),
                            onIntValueChange = { creatureVM.loreModifiers.changeToMod(key, it) },
                            modifier = Modifier.width(50.dp),
                            textStyle = when (creatureVM.loreModifiers.setups[key]) {
                                is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                                null, is StatSetup.Tier -> TextStyle.Default
                            },
                            explicitlySigned = true,
                            label = null
                        )
                        TextDropdown(creatureVM.loreModifiers.tierByStat(key), {
                            creatureVM.loreModifiers.changeToStatTier(key, it)
                        }, abilityModifiersTable[0]!!.keys.toTypedArray(),
                        modifier = Modifier.padding(end = 3.dp))
                }
            }
        }
    }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SkillView(skill: Skill, creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(modifier = modifier
        .hoverable(interactionSource = interactionSource)
        .wrapContentSize()) {
        if (creatureVM.proficientSkills[skill]!!) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                NumericTextField(
                    value = creatureVM.skillModifiers.modByStat(skill),
                    onIntValueChange = { creatureVM.skillModifiers.changeToMod(skill, it) },
                    modifier = Modifier.width(85.dp),
                    textStyle = when (creatureVM.skillModifiers.setups[skill]) {
                        is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                        null, is StatSetup.Tier -> TextStyle.Default
                    },
                    explicitlySigned = true
                ) { Text(text = skill.name) }
                TextDropdown(creatureVM.skillModifiers.tierByStat(skill), {
                    creatureVM.skillModifiers.changeToStatTier(skill, it)
                }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low, StatTier.Terrible))
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val defaultValue = "~"
                BasicTextField(value = defaultValue, onValueChange = {}, readOnly = true, modifier = Modifier.width(85.dp), textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center)) {
                    TextFieldDefaults.OutlinedTextFieldDecorationBox(
                        value = defaultValue,
                        innerTextField = it,
                        enabled = false,
                        singleLine = true,
                        label = { Text(text = skill.name) },
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                                start = 0.dp, end = 0.dp, top = 4.dp, bottom = 4.dp
                        ),
                    )
                }
                Box() {
                    Button(
                        onClick = {creatureVM.proficientSkills[skill] = !creatureVM.proficientSkills[skill]!!},
                        content = { Text("Not Prof.") },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(85.dp)
                            .height(25.dp),
                        contentPadding = PaddingValues(2.dp)
                    )
                }
            }
        }
        if (isHovered) {
            Button(
                content = { Text(text = "x") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                onClick = { creatureVM.proficientSkills[skill] = !creatureVM.proficientSkills[skill]!! },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(15.dp)
            )
        }
    }
}
