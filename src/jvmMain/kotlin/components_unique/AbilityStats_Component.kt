package components_unique

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
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
fun AbilitiesStats_Component(
    creatureVM: CreatureVM,
    modifier: Modifier = Modifier
) { //TODO Fix selection and handle empty state
    val keys = Ability.values()
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        keys.forEach { key ->
            AbilityView(creatureVM, key)
        }
    }
}

@Composable
private fun AbilityView(creatureVM: CreatureVM, key: Ability, modifier: Modifier = Modifier) {
    var label = when (key) {
        Ability.Strength -> "STR"
        Ability.Dexterity -> "DEX"
        Ability.Constitution -> "CON"
        Ability.Intelligence -> "INT"
        Ability.Wisdom -> "WIS"
        Ability.Charisma -> "CHA"
    }

    Box(
        modifier = modifier,
    ) {
        NumericTextField(
            value = creatureVM.abilityModifiers.modByStat(key),
            onIntValueChange = { creatureVM.abilityModifiers.changeToMod(key, it) },
            modifier = Modifier
                .width(60.dp)
                .height(35.dp)
                .align(Alignment.Center),
            textStyle = when (creatureVM.abilityModifiers.setups[key]?.statSetup) {
                is StatSetup.Modifier -> BoldTextStyle
                null, is StatSetup.Tier -> RegularTextStyle
            },
            explicitlySigned = true,
            label = label
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .absoluteOffset(x = 10.0.dp)
                .background(
                    Color(
                        alpha = 255,
                        red = 252,
                        green = 251,
                        blue = 246
                    )
                )
                .border(
                    width = 1.dp, color = Color(
                        alpha = 255,
                        red = 9,
                        green = 39,
                        blue = 96
                    ), shape = RoundedCornerShape(10)
                ),
            contentAlignment = Alignment.Center
        ) {
            TextDropdown(creatureVM.abilityModifiers.tierByStat(key), {
                creatureVM.abilityModifiers.changeToStatTier(key, it)
            }, abilityModifiersTable[0]!!.keys.toTypedArray(), Modifier, true)
        }
    }
}
