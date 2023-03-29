package views

import AC
import HP
import SavingThrow
import StatTier
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import components.*
import models.*

@Composable
fun DefenseStats(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    Column(modifier.padding(top = 3.dp)) {
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                NumericTextField(
                    value = creatureVM.creatureAC.modByStat(AC.AC),
                    label = "Armor Class",
                    textStyle = when (creatureVM.creatureAC.setups[AC.AC]) {
                        is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                        null, is StatSetup.Tier -> TextStyle.Default
                    },
                    modifier = Modifier.width(101.9.dp),
                    onIntValueChange = { creatureVM.creatureAC.changeToMod(AC.AC, it) },
                    explicitlySigned = false
                )
                ItemDropdown(creatureVM.creatureAC.tierByStat(AC.AC), {
                    creatureVM.creatureAC.changeToStatTier(AC.AC, it)
                }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                    NumericTextField(
                        value = creatureVM.creatureHP.modByStat(HP.HP),
                        label = "Hit Points",
                        textStyle = when (creatureVM.creatureHP.setups[HP.HP]) {
                            is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                            null, is StatSetup.Tier -> TextStyle.Default
                        },
                        modifier = Modifier.width(101.9.dp),
                        onIntValueChange = { creatureVM.creatureHP.changeToMod(HP.HP, it) },
                    )
                    ItemDropdown(creatureVM.creatureHP.tierByStat(HP.HP), {
                        creatureVM.creatureHP.changeToStatTier(HP.HP, it)
                    }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low, StatTier.Terrible))
            }
        }
        Row(Modifier.padding(top = 3.dp)) {
            SavingThrow.values().forEach { savingThrow ->
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                    Row {
                        NumericTextField(
                            value = creatureVM.creatureSavingThrows.modByStat(savingThrow),
                            label = savingThrow.name,
                            textStyle = when (creatureVM.creatureSavingThrows.setups[savingThrow]) {
                                is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                                null, is StatSetup.Tier -> TextStyle.Default
                            },
                            modifier = Modifier.width(101.9.dp),
                            onIntValueChange = { creatureVM.creatureSavingThrows.changeToMod(savingThrow, it) },
                            explicitlySigned = true
                        )
                    }
                    ItemDropdown(creatureVM.creatureSavingThrows.tierByStat(savingThrow), {
                        creatureVM.creatureSavingThrows.changeToStatTier(savingThrow, it)
                    }, StatTier.values())
                }
            }
        }
    }
}