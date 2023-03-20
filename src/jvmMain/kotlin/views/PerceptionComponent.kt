package views

import models.CreatureVM
import StatTier
import VisionType
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.ItemDropdown
import PerceptionSecondaryTrait
import SensePrecision
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import components.*

@Composable
fun PerceptionMod(creatureVM: CreatureVM) {
    Row {
        Column {
            Text(
                "Perception:   +${creatureVM.perceptionModifier}",
                modifier = Modifier.absolutePadding(top = 5.dp)
            )
            ItemDropdown(
                selected = creatureVM.perceptionTier,
                onValueChanged = { creatureVM.perceptionTier = it },
                values = StatTier.values(),
            )
        }
        Column {
            Text(" ", modifier = Modifier.absolutePadding(top = 5.dp))
            ItemDropdown(
                selected = creatureVM.vision.value,
                onValueChanged = { creatureVM.vision.value = it },
                values = VisionType.values(),
                size = 170.dp,
            )
        }
    }
    Row(Modifier.padding(top = 10.dp)) {
        PerceptionTraits(creatureVM)
    }
}

@Composable
fun PerceptionTraits(creatureVM: CreatureVM) {
    val perceptionSecondaryTraits = creatureVM.perceptionSecondaryTraits
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
                        onClick = { perceptionSecondaryTraits.add(PerceptionSecondaryTrait()) },
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