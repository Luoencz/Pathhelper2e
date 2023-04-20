package com.example.figmarelay_test.generaltraitcharacteristiccardcomponent

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import com.google.relay.compose.*
import components_unique.*

/**
 * CharacteristicCard for describing general trait of a Creature: Undead, Dragon etc.
 *
 * This composable was generated from the UI Package 'general_trait_characteristic_card_component'.
 * Generated code; do not edit directly
 */
@Composable
fun GeneralTraitCharacteristicCardComponent(
    modifier: Modifier = Modifier,
    data: CreatureCharacteristicCard.GeneralTraitCharacteristicCard
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .border(
                width = 1.dp, Color(
                    alpha = 255,
                    red = 9,
                    green = 39,
                    blue = 96
                ), shape = RoundedCornerShape(5)
            )
            .background(
                Color(
                    alpha = 255,
                    red = 246,
                    green = 243,
                    blue = 227
                )
            )
            .padding(5.dp)
    ) {
        ModifierCaption {
            RelayText(
                color = Color(
                    alpha = 255,
                    red = 96,
                    green = 27,
                    blue = 21
                ),
                height = 1.171875.em,
                textAlign = TextAlign.Left,
                maxLines = -1,
                modifier = Modifier
                    .rowWeight(1.0f)
                    .fillMaxWidth(1.0f)
                    .requiredHeight(25.0.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            ) { m, max, over, st ->
                BasicTextField(
                    value = data.name.value,
                    onValueChange = { new_value: String -> data.name.value = new_value },
                    interactionSource = interactionSource,
                    modifier = m,
                    maxLines = max,
                    //overflow = over,
                    textStyle = st,
                    enabled = true,
                    singleLine = true
                )
            }
            Tag {
                CharacteristicType_Component(type = CharacteristicType.trait)
            }
        }
        Description()
    }
}

@Composable
fun CharacteristicType_Component(modifier: Modifier = Modifier, type: CharacteristicType) {
    BasicText(
        type.name, modifier = modifier, maxLines = 1, overflow = TextOverflow.Clip, style =
        TextStyle(
            Color(
                alpha = 255,
                red = 244,
                green = 239,
                blue = 226
            ),

            fontSize = 10.sp
        )
    )
}

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 96,
            green = 27,
            blue = 21
        ),
        padding = PaddingValues(
            start = 4.0.dp,
            top = 2.0.dp,
            end = 4.0.dp,
            bottom = 2.0.dp
        ),
        itemSpacing = 8.0,
        clipToParent = false,
        radius = 4.0,
        content = content,
        modifier = modifier
    )
}

@Composable
fun ModifierCaption(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        mainAxisAlignment = MainAxisAlignment.Start,
        arrangement = RelayContainerArrangement.Row,
        clipToParent = false,
        content = content,
        modifier = modifier.fillMaxWidth(1.0f)
    )
}

@Composable
fun Description(modifier: Modifier = Modifier) {
    RelayText(
        color = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        ),
        text = AnnotatedString("Description"),
        height = 1.171875.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier
            .fillMaxWidth(1.0f)
            .fillMaxHeight(1.0f)
    )
}

@Composable
fun CardBorder(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 246,
            green = 243,
            blue = 227
        ),
        padding = PaddingValues(all = 12.0.dp),
        itemSpacing = 12.0,
        radius = 8.0,
        strokeWidth = 1.0,
        strokeColor = Color(
            alpha = 255,
            red = 9,
            green = 39,
            blue = 96
        ),
        content = content,
        modifier = modifier
            .fillMaxWidth(1.0f)
            .fillMaxHeight(1.0f)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(8.dp),
                clip = true
            )
    )
}
