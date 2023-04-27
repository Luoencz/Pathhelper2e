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
import androidx.compose.ui.unit.*
import components_general.*
import data.*
import kotlin.math.*

@Composable
fun characteristicCard(content: CreatureCharacteristic) {
    var generator: Pair<String, @Composable () -> Unit> = when (content) {
        is CreatureCharacteristic.GeneralTrait -> Pair("Trait") @Composable { GeneralTraitContent(content) }
        is CreatureCharacteristic.PerceptionSense -> Pair("Sense") @Composable { PerceptionContent(content) }
        is CreatureCharacteristic.DamageModifier -> Pair("DamageModifier") @Composable { DamageModifiersContent(content) }
        is CreatureCharacteristic.Stat -> Pair("Skill") @Composable { SkillContent(content) }
    }

    Box(
        Modifier
            .size(width = 200.dp, height = 100.dp)
            .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
            .background(BackgroundColor)
            .padding(10.dp)
    ) {
        Column {
            generator.second()
        }
    }
}

@Composable
fun GeneralTraitContent(content: CreatureCharacteristic.GeneralTrait) {
    val interactionSource = remember { MutableInteractionSource() }
    var mainPage by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .offset(y = (if (mainPage) -9 else -10).dp)
                    .wrapContentSize()
                    .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(horizontal = 3.dp)

            ) {
                BasicText("skill", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .clickable { mainPage = true }
                )
            }
            Box(
                Modifier
                    .offset(y = (if (!mainPage) -9 else -10).dp)
                    .wrapContentSize()
                    .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(horizontal = 3.dp)

            ) {
                BasicText("description", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .clickable {
                            mainPage = false
                        }
                )
            }
        }

        if (mainPage) {
            Row(
                Modifier
                    .fillMaxWidth()
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
                        "Trait", maxLines = 1, style = RegularTextStyle.merge(
                            TextStyle(color = Color.White)
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(3.dp)
                    )
                }
            }
        } else {
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
}

@Composable
fun SkillContent(content: CreatureCharacteristic.Stat) {
    val interactionSource = remember { MutableInteractionSource() }
    var mainPage by remember { mutableStateOf(true) }

    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .offset(y = (if (mainPage) -9 else -10).dp)
                    .wrapContentSize()
                    .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(horizontal = 3.dp)

            ) {
                BasicText("skill", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .clickable { mainPage = true }
                )
            }
            Box(
                Modifier
                    .offset(y = (if (!mainPage) -9 else -10).dp)
                    .wrapContentSize()
                    .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(horizontal = 3.dp)

            ) {
                BasicText("description", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .clickable {
                            mainPage = false
                        }
                )
            }
        }

        if (mainPage) {
            Row(
                Modifier
                    .fillMaxWidth()
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
                        "Skill", maxLines = 1, style = RegularTextStyle.merge(
                            TextStyle(color = Color.White)
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(3.dp)
                    )
                }
            }
            TierStatView(stat = content.stat.value, explicitlySigned = true)
        } else {
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
}

@Composable
fun DamageModifiersContent(content: CreatureCharacteristic.DamageModifier) {
    val interactionSource = remember { MutableInteractionSource() }
    var mainPage by remember { mutableStateOf(true) }


    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .offset(y = (if (mainPage) -9 else -10).dp)
                    .wrapContentSize()
                    .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(horizontal = 3.dp)

            ) {
                BasicText("skill", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .clickable { mainPage = true }
                )
            }
            Box(
                Modifier
                    .offset(y = (if (!mainPage) -9 else -10).dp)
                    .wrapContentSize()
                    .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(horizontal = 3.dp)

            ) {
                BasicText("description", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .clickable {
                            mainPage = false
                        }
                )
            }
        }
        if (mainPage) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Box(
                    Modifier
                        .wrapContentSize()
                        .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                        .padding(3.dp)
                ) {
                    Row {
                        BasicText("Weak", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .clickable { content.modifierType.value = DamageModifierType.Weakness }
                        )
                        BasicText(
                            "/", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                        )
                        BasicText("Resistant",
                            maxLines = 1,
                            style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .clickable { content.modifierType.value = DamageModifierType.Resistance }
                        )
                        BasicText(
                            "/", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                        )
                        BasicText("Immune",
                            maxLines = 1,
                            style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .clickable { content.modifierType.value = DamageModifierType.Weakness }
                        )
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(
                    value = content.damageType.value,
                    onValueChange = { content.damageType.value = it },
                    interactionSource = interactionSource,
                    singleLine = true,
                    textStyle = RegularTextStyle.merge(TextStyle(color = TitleColor, fontSize = 17.sp)),
                    modifier = Modifier.width(120.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                NumericTextField(
                    value = content.value.value,
                    onIntValueChange = {
                        content.value.value = it
                    },
                    modifier = Modifier
                        .width(60.dp)
                        .height(35.dp)
                )
            }
        }
        else {
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
}

@Composable
fun PerceptionContent(content: CreatureCharacteristic.PerceptionSense) {
    val interactionSource = remember { MutableInteractionSource() }
    var mainPage by remember { mutableStateOf(true) }


    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .offset(y = (if (mainPage) -9 else -10).dp)
                    .wrapContentSize()
                    .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(horizontal = 3.dp)

            ) {
                BasicText("skill", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .clickable { mainPage = true }
                )
            }
            Box(
                Modifier
                    .offset(y = (if (!mainPage) -9 else -10).dp)
                    .wrapContentSize()
                    .background(TitleColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(horizontal = 3.dp)

            ) {
                BasicText("description", maxLines = 1, style = RegularTextStyle.merge(TextStyle(color = Color.White)),
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .clickable {
                            mainPage = false
                        }
                )
            }
        }

        if (mainPage) {
            Row(
                Modifier
                    .fillMaxWidth()
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
                        "Sense", maxLines = 1, style = RegularTextStyle.merge(
                            TextStyle(color = Color.White)
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(3.dp)
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextDropdown(
                    selected = content.precision.value,
                    onValueChanged = {
                        content.precision.value = it
                    },
                    values = SensePrecision.values(),
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(70.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
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
        }
        else {
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
}