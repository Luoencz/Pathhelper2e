package com.example.figmarelay_test.generaltraitcharacteristiccardcomponent

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.google.relay.compose.CrossAxisAlignment
import com.google.relay.compose.MainAxisAlignment
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerArrangement
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayText
import components_unique.CharacteristicType
import components_unique.CreatureCharacteristicCard

/**
 * CharacteristicCard for describing general trait of a Creature: Undead, Dragon etc.
 *
 * This composable was generated from the UI Package 'general_trait_characteristic_card_component'.
 * Generated code; do not edit directly
 */
@Composable
fun GeneralTraitCharacteristicCardComponent(modifier: Modifier = Modifier, data: CreatureCharacteristicCard.GeneralTraitCharacteristicCard) {
    val interactionSource = remember { MutableInteractionSource() }

    TopLevel(modifier = modifier) {
        ModifierCaption(modifier = Modifier.rowWeight(1.0f)) {
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
                modifier = Modifier.rowWeight(1.0f).fillMaxWidth(1.0f).requiredHeight(25.0.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            ) { m, max, over, st ->
                BasicTextField(
                    value = data.name.value,
                    onValueChange = {new_value: String -> data.name.value = new_value},
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
        Description(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
    }
}

@Composable
fun CharacteristicType_Component(modifier: Modifier = Modifier, type: CharacteristicType) {
    RelayText(
        fontSize = 10.0.sp,
        color = Color(
            alpha = 255,
            red = 244,
            green = 239,
            blue = 226
        ),
        height = 1.171875.em,
        modifier = modifier.wrapContentHeight(align = Alignment.CenterVertically)
    ) { m, max, over, st ->
        BasicText(type.name, modifier = m, maxLines = max, overflow = over, style = st)
    }
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
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
        arrangement = RelayContainerArrangement.Row,
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
        height = 1.171875.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun TopLevel(
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
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
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
        modifier = modifier.fillMaxWidth(1.0f).fillMaxHeight(1.0f).shadow(
            elevation = 3.dp,
            shape = RoundedCornerShape(8.dp),
            clip = true
        )
    )
}
