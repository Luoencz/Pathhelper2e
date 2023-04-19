package components_unique

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import com.google.relay.compose.*
import components_general.*
import data.*
import models.*

@Composable
fun AC_Component(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        NumericTextField(
            value = creatureVM.creatureAC.modByStat(AC.AC),
            onIntValueChange = { creatureVM.creatureAC.changeToMod(AC.AC, it) },
            modifier = Modifier.width(85.dp),
            textStyle = when (creatureVM.creatureAC.setups[AC.AC]) {
                is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                null, is StatSetup.Tier -> TextStyle.Default
            },
            explicitlySigned = false,
            label = "AC"
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
            content = { TextDropdown(creatureVM.creatureAC.tierByStat(AC.AC), {
                creatureVM.creatureAC.changeToStatTier(AC.AC, it)
            }, abilityModifiersTable[0]!!.keys.toTypedArray(), Modifier,true)
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}