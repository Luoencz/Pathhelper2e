package views

import data.AC
import data.HP
import data.SavingThrow
import data.StatTier
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import components.*
import models.*

@Composable
fun DefenseStats(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 8.dp)) {
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                NumericTextField(
                    value = creatureVM.creatureAC.modByStat(AC.AC),
                    onIntValueChange = { creatureVM.creatureAC.changeToMod(AC.AC, it) },
                    modifier = Modifier.width(85.dp),
                    textStyle = when (creatureVM.creatureAC.setups[AC.AC]) {
                        is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                        null, is StatSetup.Tier -> TextStyle.Default
                    },
                    explicitlySigned = false
                ) { Text(text = "Armor Class") }
                TextDropdown(creatureVM.creatureAC.tierByStat(AC.AC), {
                    creatureVM.creatureAC.changeToStatTier(AC.AC, it)
                }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                    NumericTextField(
                        value = creatureVM.creatureHP.modByStat(HP.HP),
                        onIntValueChange = { creatureVM.creatureHP.changeToMod(HP.HP, it) },
                        modifier = Modifier.width(85.dp),
                        textStyle = when (creatureVM.creatureHP.setups[HP.HP]) {
                            is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                            null, is StatSetup.Tier -> TextStyle.Default
                        },
                    ) { Text(text = "Hit Points") }
                TextDropdown(creatureVM.creatureHP.tierByStat(HP.HP), {
                        creatureVM.creatureHP.changeToStatTier(HP.HP, it)
                    }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low, StatTier.Terrible))
            }
        }
        Row(Modifier.padding(top = 8.dp)) {
            SavingThrow.values().forEach { savingThrow ->
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                    Row {
                        NumericTextField(
                            value = creatureVM.creatureSavingThrows.modByStat(savingThrow),
                            onIntValueChange = { creatureVM.creatureSavingThrows.changeToMod(savingThrow, it) },
                            modifier = Modifier.width(85.dp),
                            textStyle = when (creatureVM.creatureSavingThrows.setups[savingThrow]) {
                                is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                                null, is StatSetup.Tier -> TextStyle.Default
                            },
                            explicitlySigned = true
                        ) { Text(text = savingThrow.name) }
                    }
                    TextDropdown(creatureVM.creatureSavingThrows.tierByStat(savingThrow), {
                        creatureVM.creatureSavingThrows.changeToStatTier(savingThrow, it)
                    }, StatTier.values())
                }
            }
        }
    }
}