package components_unique

import PerceptionTrait
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import components_general.*
import data.*
import models.*

@Composable
fun Senses_Component(creatureVM: CreatureVM) {
    NamedList(traitsList = creatureVM.perceptionTraits,
        lambdaConstructor = ::PerceptionTrait,
        content =
        { namedObject ->
                NamedBubble(
                    trait = namedObject,
                    modifier = Modifier.padding(end = 2.dp),
                    traitsList = creatureVM.perceptionTraits,
                    content = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = it.name,
                                maxLines = 1,
                                modifier = Modifier
                                    .widthIn(1.dp, Dp.Infinity)
                                    .padding(start = 3.dp, end = 3.dp)
                                    .border(1.dp, Color.Gray, RoundedCornerShape(10))
                                    .padding(5.dp)
                            )
                            TextDropdown(
                                selected = creatureVM.perceptionTraits.find { it.name == namedObject.name }!!.sensePrecision,
                                onValueChanged = {
                                    creatureVM.perceptionTraits.find { it.name == namedObject.name }!!.sensePrecision =
                                        it
                                },
                                values = SensePrecision.values(),
                            )
                            NumericTextField(
                                value = creatureVM.perceptionTraits.find { it.name == namedObject.name }!!.range,
                                onIntValueChange = {
                                    creatureVM.perceptionTraits.find { it.name == namedObject.name }!!.range =
                                        it
                                },
                                modifier = Modifier
                                    .width(68.dp)
                                    .padding(start = 3.dp, end = 0.5.dp)
                            )
                            Text(text = "ft", color = Color.Gray)
                        }
                    }
                )
        },
        label = { Text(text = "Sense") }
    )
}