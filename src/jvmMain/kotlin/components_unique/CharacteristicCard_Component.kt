package components_unique

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import components_general.*
import data.*

@Composable
fun characteristicCard(content: CreatureCharacteristic) {
    val interactionSource = remember { MutableInteractionSource() }
    var generator: Pair<String, @Composable ()-> Unit> = when (content) {
        is CreatureCharacteristic.GeneralTrait -> Pair("Trait") @Composable { GeneralTraitContent(content) }
        is CreatureCharacteristic.PerceptionSense -> TODO()
        is CreatureCharacteristic.Resistance -> TODO()
        is CreatureCharacteristic.Ability -> TODO()
    }

    Box(
        Modifier
            .size(200.dp)
            .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
            .background(BackgroundColor)
            .padding(10.dp)
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                BasicTextField(
                    value = content.name.value,
                    onValueChange = { content.name.value = it },
                    interactionSource = interactionSource,
                    singleLine = true,
                    textStyle = RegularTextStyle.merge(TextStyle(color = TitleColor, fontSize = 17.sp))
                )
                Spacer(Modifier.weight(1f))
                Box(
                    Modifier
                        .wrapContentSize()
                        .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))) {
                    BasicText(
                        generator.first, maxLines = 1, overflow = TextOverflow.Clip, style = RegularTextStyle.merge(
                            TextStyle(color = Color.White)),
                            modifier = Modifier.padding(3.dp)
                        )
                }
            }
            generator.second()
        }
    }
}

@Composable
fun GeneralTraitContent(content: CreatureCharacteristic.GeneralTrait) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier
        .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
        .padding(3.dp)
        .fillMaxSize()) {
        BasicTextField(
            value = content.description.value,
            onValueChange = { content.description.value = it },
            interactionSource = interactionSource,
            textStyle = RegularTextStyle,
            modifier = Modifier.fillMaxSize()
        )
    }
}
@Composable
fun SkillContent(content: CreatureCharacteristic.Ability) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = Modifier
        .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
        .padding(3.dp)
        .fillMaxSize()) {

        //TierStatView(statMap = content.stat, key = )
        BasicTextField(
            value = content.description.value,
            onValueChange = { content.description.value = it },
            interactionSource = interactionSource,
            textStyle = RegularTextStyle,
            modifier = Modifier.fillMaxSize()
        )
    }
}
