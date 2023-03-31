package views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import components.ItemDropdown
import PerceptionTrait
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import components.*
import data.*
import models.*

@Composable
fun PerceptionStats(creatureVM: CreatureVM, modifier: Modifier) {
    Column(modifier) {
        Row {
            Column {
                NumericTextField(
                    value = creatureVM.creaturePerception.modByStat(Perception.Perception),
                    onIntValueChange = { creatureVM.creaturePerception.changeToMod(Perception.Perception, it) },
                    modifier = Modifier.width(103.dp),
                    textStyle = when (creatureVM.creaturePerception.setups[Perception.Perception]) {
                        is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                        null, is StatSetup.Tier -> TextStyle.Default
                    },
                    explicitlySigned = true,
                    label = { Text(text = "Perception") }
                )
                ItemDropdown(creatureVM.creaturePerception.tierByStat(Perception.Perception), {
                    creatureVM.creaturePerception.changeToStatTier(Perception.Perception, it)
                }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low, StatTier.Terrible))
            }
        }
        Row(Modifier.padding(top = 10.dp)) {
            //PerceptionTraits(creatureVM)
            NamedList(traitsList = creatureVM.perceptionTraits,
                lambdaConstructor = { name -> PerceptionTrait(name)},
                content =
                { namedObject ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                            NamedBubble(
                                trait = namedObject,
                                modifier = Modifier.padding(horizontal = 1.dp),
                                traitsList = creatureVM.perceptionTraits,
                                content = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = it.name,
                                            maxLines = 1,
                                            modifier = Modifier
                                                .widthIn(1.dp, Dp.Infinity).padding(start = 3.dp, end = 3.dp).border(1.dp, Color.Gray, RoundedCornerShape(10)).padding(5.dp)
                                        )
                                        ItemDropdown(
                                            selected = creatureVM.perceptionTraits.find { it.name == namedObject.name }!!.sensePrecision,
                                            onValueChanged = {
                                                creatureVM.perceptionTraits.find { it.name == namedObject.name }!!.sensePrecision =
                                                    it
                                            },
                                            values = SensePrecision.values(),
                                            size = Modifier.size(80.dp, 35.dp)
                                        )
                                        NumericTextField(
                                            value = creatureVM.perceptionTraits.find { it.name == namedObject.name }!!.range,
                                            onIntValueChange = {
                                                creatureVM.perceptionTraits.find { it.name == namedObject.name }!!.range =
                                                    it
                                            },
                                            label = { Text(text = "Range") },
                                            modifier = Modifier.width(70.dp).padding(start = 3.dp, end = 3.dp)
                                        )
                                    }
                                }
                            )
                    }
                },
                label = {Text(text = "New Sense")}
            )
        }
    }
}

/*
@Composable
fun PerceptionTraits(creatureVM: CreatureVM) {
    val perceptionSecondaryTraits = creatureVM.perceptionTraits
    val pattern = remember { Regex("^[0-9]*\\.*-?[0-9]+\$") }

    HorizontalFlow {
        var i = 0
        while (i <= perceptionSecondaryTraits.size + 1) {
            val index = i
            if (index < perceptionSecondaryTraits.size) {
                val trait = perceptionSecondaryTraits[index]
                Row {
                    OutlinedTextField(
                        trait.name,
                        { trait.name = it },
                        modifier = Modifier.width(200.dp)
                    )
                    Column {
                        ItemDropdown(
                            selected = trait.sensePrecision,
                            onValueChanged = { trait.sensePrecision = it },
                            values = SensePrecision.values(),
                        )
                        Row {
                            Box(Modifier.border(1.dp, Color.Gray)) {
                                BasicTextField(
                                    value = TextFieldValue(
                                        "${trait.range}",
                                        selection = TextRange(trait.range.toString().length + 2),
                                    ),
                                    onValueChange = {
                                        when {
                                            it.text.isEmpty() -> trait.range = 0
                                            it.text.matches(pattern) -> trait.range = it.text.toInt()
                                            //Force recompose
                                            else -> trait.range = trait.range
                                        }
                                    },
                                    modifier = Modifier
                                        .width(70.dp)
                                        .height(26.dp)
                                )
                            }
                            Text(text = "ft")
                        }
                    }
                }
            }

            if (index == perceptionSecondaryTraits.size) {
                Column(verticalArrangement = Arrangement.Top) {
                    Button(
                        onClick = { perceptionSecondaryTraits.add(PerceptionTrait()) },
                        Modifier.size(27.5f.dp),
                        contentPadding = PaddingValues(2.dp),
                    ) {
                        Text(text = "+", textAlign = TextAlign.Center, fontSize = 7.sp)
                    }
                    Button(
                        onClick = { perceptionSecondaryTraits.removeAt(perceptionSecondaryTraits.size - 1) },
                        Modifier.size(27.5f.dp),
                        contentPadding = PaddingValues(2.dp),
                    ) {
                        Text("-", textAlign = TextAlign.Center, fontSize = 7.sp)
                    }
                }
            }
            i++
        }
    }
}

*/