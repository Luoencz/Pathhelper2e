package components_unique

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
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import components_general.*
import data.*

@Composable
fun characteristicCard(content: CreatureCharacteristic) {
    val interactionSource = remember { MutableInteractionSource() }
    var generator: Pair<String, @Composable () -> Unit> = when (content) {
        is CreatureCharacteristic.GeneralTrait -> Pair("Trait") @Composable { GeneralTraitContent(content) }
        is CreatureCharacteristic.PerceptionSense -> Pair("Sense") @Composable { PerceptionContent(content)}
        is CreatureCharacteristic.Resistance -> TODO()
        is CreatureCharacteristic.Stat -> Pair("Skill") @Composable { SkillContent(content) }
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
                    textStyle = RegularTextStyle.merge(TextStyle(color = TitleColor, fontSize = 17.sp)),
                    modifier = Modifier.width(110.dp)
                )
                Spacer(Modifier.weight(1f))
                Box(
                    Modifier
                        .wrapContentSize()
                        .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                ) {
                    BasicText(
                        generator.first, maxLines = 1, style = RegularTextStyle.merge(
                            TextStyle(color = Color.White)
                        ),
                        modifier = Modifier.wrapContentWidth().padding(3.dp)
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

    Box(
        modifier = Modifier
            .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
            .padding(3.dp)
            .fillMaxSize()
    ) {
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
fun SkillContent(content: CreatureCharacteristic.Stat) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        TierStatView(stat = content.stat.value, explicitlySigned = true)

        Box(
            modifier = Modifier
                .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
                .padding(3.dp)
                .fillMaxSize()
        ) {
            BasicTextField(
                value = content.description.value,
                onValueChange = { content.description.value = it },
                interactionSource = interactionSource,
                textStyle = RegularTextStyle,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun PerceptionContent(content: CreatureCharacteristic.PerceptionSense) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                NumericTextField(
                    value = content.range.value,
                    onIntValueChange = {
                        content.range.value = it
                    },
                    modifier = Modifier
                        .width(60.dp)
                        .height(35.dp)
                )
                Text(text = "ft", color = Color.Gray)
            }
            TextDropdown(
                selected = content.precision.value,
                onValueChanged = {
                    content.precision.value = it
                },
                values = SensePrecision.values(),
            )
        }

        Box(
            modifier = Modifier
                .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
                .padding(3.dp)
                .fillMaxSize()
        ) {
            BasicTextField(
                value = content.description.value,
                onValueChange = { content.description.value = it },
                interactionSource = interactionSource,
                textStyle = RegularTextStyle,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}