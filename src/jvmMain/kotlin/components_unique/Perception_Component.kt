package components_unique

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import com.google.relay.compose.*
import components_general.*
import data.*
import models.*

@Composable
fun Perception_Component(creatureVM: CreatureVM) {
    Box(contentAlignment = Alignment.Center) {
        NumericTextField(
            value = creatureVM.creaturePerception.modByStat(Perception.Perception),
            onIntValueChange = { creatureVM.creaturePerception.changeToMod(Perception.Perception, it) },
            modifier = Modifier.width(60.dp).height(35.dp),
            textStyle = when (creatureVM.creaturePerception.setups[Perception.Perception]) {
                is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                null, is StatSetup.Tier -> TextStyle.Default
            },
            explicitlySigned = true,
        )
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
            content = {  TextDropdown(creatureVM.creaturePerception.tierByStat(Perception.Perception), {
                creatureVM.creaturePerception.changeToStatTier(Perception.Perception, it)
            }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low, StatTier.Terrible), showOnlyFirstLetter = true)},
            modifier = Modifier.align(Alignment.CenterEnd).offset(x = 10.0.dp)
        )
    }
}