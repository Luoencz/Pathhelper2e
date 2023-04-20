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
fun SavingThrows_Component(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        SavingThrow.values().forEach { savingThrow ->
            Box(contentAlignment = Alignment.Center, modifier = modifier) {
                    NumericTextField(
                        value = creatureVM.creatureSavingThrows.modByStat(savingThrow),
                        onIntValueChange = { creatureVM.creatureSavingThrows.changeToMod(savingThrow, it) },
                        modifier = Modifier.width(60.dp).height(35.dp),
                        textStyle = when (creatureVM.creatureSavingThrows.setups[savingThrow]?.statSetup) {
                            is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                            null, is StatSetup.Tier -> TextStyle.Default
                        },
                        explicitlySigned = true,
                        label = when(savingThrow) {
                            SavingThrow.Reflex -> "Ref"
                            SavingThrow.Will -> "Will"
                            SavingThrow.Fortitude -> "Fort"
                        }
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
                    content = {  TextDropdown(creatureVM.creatureSavingThrows.tierByStat(savingThrow), {
                        creatureVM.creatureSavingThrows.changeToStatTier(savingThrow, it)
                    }, StatTier.values(), showOnlyFirstLetter = true)},
                    modifier = Modifier.align(Alignment.CenterEnd).offset(x = 10.0.dp)
                )

            }
        }
    }
}