package components_unique

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.figmarelay_test.generaltraitcharacteristiccardcomponent.GeneralTraitCharacteristicCardComponent
import data.Skill

enum class CharacteristicType {
    trait,
    skill,
    sense
}

sealed interface CreatureCharacteristicCard {
    var name: MutableState<String>
    var type: CharacteristicType

    data class SkillCharacteristicCard(override var type: CharacteristicType, var skill: Skill, override var name: MutableState<String> = mutableStateOf(skill.name)) : CreatureCharacteristicCard
    data class GeneralTraitCharacteristicCard(override var name: MutableState<String>, override var type: CharacteristicType, var description: String) : CreatureCharacteristicCard
}

@Composable
fun SkillCharacteristicCardComponent(data: MutableState<CreatureCharacteristicCard.SkillCharacteristicCard>) {
    //SkillView(data.value.skill, creatureVM)
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun Outdated_GeneralTraitCharacteristicCardComponent(data: CreatureCharacteristicCard.GeneralTraitCharacteristicCard) {
        val interactionSource = remember { MutableInteractionSource() }

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            BasicTextField(
                value = data.name.value,
                onValueChange = { data.name.value = it },
                interactionSource = interactionSource,
                enabled = true,
                singleLine = true,
                modifier = Modifier
                    .widthIn(10.dp, Dp.Infinity).padding(16.dp).width(100.dp)
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = data.name.value,
                    visualTransformation = VisualTransformation.None,
                    innerTextField = it,
                    singleLine = true,
                    enabled = true,
                    label = { Text(text = "Name") },
                    interactionSource = interactionSource,
                    // keep vertical paddings but change the horizontal
                    contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                        start = 8.dp, end = 8.dp
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors()
                )
            }
            Text(data.type.name, Modifier.padding(16.dp))
        }
    }
}

@Composable
fun characteristicCard(content: CreatureCharacteristicCard) {
    Box(Modifier.size(200.dp)) {
            when (content) {
                is CreatureCharacteristicCard.SkillCharacteristicCard -> SkillCharacteristicCardComponent(mutableStateOf(content))
                is CreatureCharacteristicCard.GeneralTraitCharacteristicCard -> GeneralTraitCharacteristicCardComponent(data = content)
            }
    }
}