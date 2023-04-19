package components_unique

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RowScopeInstanceImpl.align
import components_general.*
import data.*
import models.*

@Composable
fun AbilitiesStats_Component(creatureVM: CreatureVM, modifier: Modifier = Modifier) { //TODO Fix selection and handle empty state
    val keys = Ability.values()
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
        keys.forEach { key ->
            AbilityView(creatureVM, key)
        }
    }
}

@Composable
private fun AbilityView(creatureVM: CreatureVM, key: Ability, modifier: Modifier = Modifier) {
    var label = when(key) {
        Ability.Strength -> "STR"
        Ability.Dexterity -> "DEX"
        Ability.Constitution -> "CON"
        Ability.Intelligence -> "INT"
        Ability.Wisdom -> "WIS"
        Ability.Charisma -> "CHA"
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
                NumericTextField(
                    value = creatureVM.abilityModifiers.modByStat(key),
                    onIntValueChange = { creatureVM.abilityModifiers.changeToMod(key, it) },
                    modifier = Modifier.width(85.dp),
                    textStyle = when (creatureVM.abilityModifiers.setups[key]) {
                        is StatSetup.Modifier -> TextStyle(fontWeight = FontWeight.Bold)
                        null, is StatSetup.Tier -> TextStyle.Default
                    },
                    explicitlySigned = true,
                    label = label
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
            content = { TextDropdown(creatureVM.abilityModifiers.tierByStat(key), {
                creatureVM.abilityModifiers.changeToStatTier(key, it)
            }, abilityModifiersTable[0]!!.keys.toTypedArray(), Modifier,true)
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}
