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
            Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(start = 3.dp)) {
                Row {
                Text(text = "AC:")
                BasicTextField(
                    value = creatureVM.creatureAC.modByStat(AC.AC).toString(),
                    modifier = Modifier
                        .border(1.dp, Color.Gray)
                        .size(20.dp, 20.dp)
                        .wrapContentHeight()
                        .padding(2.dp)
                        .wrapContentWidth(),
                    textStyle = if (creatureVM.creatureAC.setups[AC.AC] is StatSetup.Modifier) TextStyle(
                        fontWeight = FontWeight.Bold
                    ) else TextStyle.Default,
                    onValueChange = {
                        when {
                            it.isEmpty() -> creatureVM.creatureAC.changeToMod(AC.AC, 0)
                            it.toIntOrNull() != null -> creatureVM.creatureAC.changeToMod(
                                AC.AC,
                                it.toInt()
                            )
                        }
                    }
                )
                }
                ItemDropdown(creatureVM.creatureAC.tierByStat(AC.AC), {
                    creatureVM.creatureAC.changeToStatTier(AC.AC, it)
                }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                Row {
                    Text(text = "HP:")
                    BasicTextField(
                        value = creatureVM.creatureHP.modByStat(HP.HP).toString(),
                        modifier = Modifier
                            .border(1.dp, Color.Gray)
                            .wrapContentWidth()
                            .size(28.dp,20.dp)
                            .padding(2.dp),
                        textStyle = if (creatureVM.creatureHP.setups[HP.HP] is StatSetup.Modifier) TextStyle(
                            fontWeight = FontWeight.Bold
                        ) else TextStyle.Default,
                        onValueChange = {
                            when {
                                it.isEmpty() -> creatureVM.creatureHP.changeToMod(HP.HP, 0)
                                it.toIntOrNull() != null -> creatureVM.creatureHP.changeToMod(
                                    HP.HP,
                                    it.toInt()
                                )
                            }
                        }
                    )}
                    ItemDropdown(creatureVM.creatureHP.tierByStat(HP.HP), {
                        creatureVM.creatureHP.changeToStatTier(HP.HP, it)
                    }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low, StatTier.Terrible))
                }
        }
    }
    Row(Modifier.padding(top = 3.dp)) {
        SavingThrow.values().forEach { savingThrow ->
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 3.dp)) {
                Row {
                    Text(text = "${savingThrow.name}:")
                    BasicTextField(
                        value = creatureVM.creatureSavingThrows.modByStat(savingThrow).toString(),
                        modifier = Modifier
                            .border(1.dp, Color.Gray)
                            .size(20.dp, 20.dp)
                            .wrapContentHeight()
                            .padding(2.dp)
                            .wrapContentWidth(),
                        textStyle = if (creatureVM.creatureSavingThrows.setups[savingThrow] is StatSetup.Modifier) TextStyle(
                            fontWeight = FontWeight.Bold
                        ) else TextStyle.Default,
                        onValueChange = {
                            when {
                                it.isEmpty() -> creatureVM.creatureSavingThrows.changeToMod(savingThrow, 0)
                                it.toIntOrNull() != null -> creatureVM.creatureSavingThrows.changeToMod(
                                    savingThrow,
                                    it.toInt()
                                )
                            }
                        }
                    )
                }
                ItemDropdown(creatureVM.creatureSavingThrows.tierByStat(savingThrow), {
                    creatureVM.creatureSavingThrows.changeToStatTier(savingThrow, it)
                }, StatTier.values())
            }
        }
    }
}