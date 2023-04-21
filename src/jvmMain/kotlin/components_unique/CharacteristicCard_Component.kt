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
fun characteristicCard(content: CreatureCharacteristicCard) {
    Box(Modifier.size(200.dp)) {
            when (content) {
                is CreatureCharacteristicCard.SkillCharacteristicCard -> SkillCharacteristicCardComponent(mutableStateOf(content))
                is CreatureCharacteristicCard.GeneralTraitCharacteristicCard -> GeneralTraitCharacteristicCardComponent(data = content)
            }
    }
}