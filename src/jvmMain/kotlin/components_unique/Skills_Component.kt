package components_unique

import data.StatTier
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
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
                    explicitlySigned = false
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
                        onClick = {},
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

