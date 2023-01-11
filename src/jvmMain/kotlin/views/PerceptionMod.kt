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
import components.DropdownWithColor

@Composable
fun PerceptionMod(creatureVM: CreatureVM) {
    Row {
        Column {
            Text(
                "Perception:   +${creatureVM.perceptionModifier}",
                modifier = Modifier.absolutePadding(top = 5.dp)
            )
            DropdownWithColor(
                selected = creatureVM.perceptionTier,
                onValueChanged = { creatureVM.perceptionTier = it },
                values = StatTier.values(),
            )
        }
        Column {
            Text(" ", modifier = Modifier.absolutePadding(top = 5.dp))
            DropdownWithColor(
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