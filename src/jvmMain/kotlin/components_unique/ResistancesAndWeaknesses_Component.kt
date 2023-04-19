package components_unique

import ReceivedDamageModifierTrait
import ReceivedDamageModifierType
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
import models.*

@Composable
fun ResistancesAndWeaknesses_Component(creatureVM: CreatureVM) {
    val traitsList = creatureVM.creatureReceivedDamageModifiers
    NamedList(traitsList = traitsList,
        lambdaConstructor = { name -> ReceivedDamageModifierTrait(name) },
        content =
        { namedObject ->
            NamedBubble(
                trait = namedObject,
                modifier = Modifier.padding(end = 2.dp),
                traitsList = traitsList,
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
                            selected = traitsList.find { it.name == namedObject.name }!!.receivedDamageModifierType,
                            onValueChanged = {
                                traitsList.find { it.name == namedObject.name }!!.receivedDamageModifierType =
                                    it
                            },
                            values = ReceivedDamageModifierType.values(),
                        )

                        if(traitsList.find { it.name == namedObject.name }!!.receivedDamageModifierType != ReceivedDamageModifierType.Immunity) {
                            NumericTextField(
                                value = traitsList.find { it.name == namedObject.name }!!.traitValue,
                                onIntValueChange = {
                                    traitsList.find { it.name == namedObject.name }!!.traitValue =
                                        it
                                },
                                modifier = Modifier
                                    .width(68.dp)
                                    .padding(start = 3.dp, end = 0.5.dp)
                            )
                        } else {
                            traitsList.find { it.name == namedObject.name }!!.traitValue = 0
                        }

                    }
                }
            )
        },
        label = { Text(text = "Damage Type") }
    )
}