package relay_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.zIndex
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayImage
import com.google.relay.compose.RelayText
import models.CreatureVM

val impact: FontFamily = FontFamily.Default

@Composable
fun Identity(creatureVM: CreatureVM) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        radius = 4.0,
        strokeWidth = 0.0,
        strokeColor = Color(
            alpha = 255,
            red = 90,
            green = 39,
            blue = 96
        ),
        content = {
            Picture(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.Center,
                    offset = DpOffset(
                        x = 0.dp,
                        y = 0.dp
                    )
                )
            )
            Name(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopCenter,
                    offset = DpOffset(
                        x = 0.dp,
                        y = -13.dp
                    )
                )
            ) {
                CreatureName_Component(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f), creatureVM)
            }
            Level(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.BottomCenter,
                    offset = DpOffset(
                        x = 0.dp,
                        y = 13.0.dp
                    )
                )
            ) {
                CL_Component(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f), creatureVM)
            }
        },
        modifier = Modifier.requiredWidth(200.0.dp).requiredHeight(200.0.dp)
    )
}

@Composable
fun Level(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 252,
            green = 251,
            blue = 246
        ),
        isStructured = false,
        radius = 4.0,
        strokeWidth = 1.0,
        strokeColor = Color(
            alpha = 255,
            red = 9,
            green = 39,
            blue = 96
        ),
        content = content,
        modifier = modifier.requiredWidth(66.0.dp).requiredHeight(24.0.dp)
    )
}

@Composable
fun CL_Component(modifier: Modifier = Modifier, creatureVM: CreatureVM) {
    var popupControl by remember { mutableStateOf(false) }

    RelayText(
        fontFamily = impact,
        color = Color(
            alpha = 255,
            red = 9,
            green = 39,
            blue = 96
        ),
        height = 1.2197265625.em,
        maxLines = -1,
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 4.0.dp,
                end = 4.0.dp,
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f).wrapContentHeight(align = Alignment.CenterVertically)
    ) { m, max, over, st ->
        BasicText(
            "Level: ${creatureVM.creatureLevel}",
            modifier = m.clickable { popupControl = !popupControl },
            maxLines = max,
            overflow = over,
            style = st,
        )
    }
}

@Composable
fun CreatureName_Component(modifier: Modifier = Modifier, creatureVM: CreatureVM) {
    val interactionSource = remember { MutableInteractionSource() }

    RelayText(
        fontFamily = impact,
        color = Color(
            alpha = 255,
            red = 9,
            green = 39,
            blue = 96
        ),
        height = 1.2197265625.em,
        maxLines = -1,
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 4.0.dp,
                end = 4.0.dp,
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f).wrapContentHeight(align = Alignment.CenterVertically)
    ) { m, max, over, st ->
        BasicTextField(
            value = creatureVM.creatureName,
            onValueChange = { new_value: String -> creatureVM.creatureName = new_value },
            interactionSource = interactionSource,
            modifier = m,
            maxLines = max,
            //overflow = over,
            textStyle = st,
            enabled = true,
            singleLine = true
        )
    }
}

@Composable
fun Name(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 252,
            green = 251,
            blue = 246
        ),
        isStructured = false,
        radius = 4.0,
        strokeWidth = 1.0,
        strokeColor = Color(
            alpha = 255,
            red = 9,
            green = 39,
            blue = 96
        ),
        content = content,
        modifier = modifier.requiredWidth(136.0.dp).requiredHeight(24.0.dp)
    )
}

@Composable
fun Picture(modifier: Modifier = Modifier) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 252,
            green = 251,
            blue = 246
        ),
        isStructured = false,
        radius = 4.0,
        strokeWidth = 1.0,
        strokeColor = Color(
            alpha = 255,
            red = 9,
            green = 39,
            blue = 96
        ),
        content = {RelayImage(
            image = painterResource("main_frame_picture.png"),
            contentScale = ContentScale.Crop,
            modifier = modifier.requiredWidth(184.0.dp).requiredHeight(198.0.dp)
        )},
        modifier = modifier.requiredWidth(200.0.dp).requiredHeight(204.0.dp)
    )
}