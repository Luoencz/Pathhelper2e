package models

import data.Ability
import Alignment
import PerceptionSecondaryTrait
import Rarity
import Size
import data.Skill
import StatTier
import VisionType
import abilityModifiersTable
import androidx.compose.runtime.*
import perceptionTable
import skillTable

class CreatureVM {
    var creatureName by mutableStateOf("")
    var creatureLevel by mutableStateOf(0)

    val creatureRarity = mutableStateOf(Rarity.Common)
    val creatureAlignment = mutableStateOf(Alignment.TN)
    val creatureSize = mutableStateOf(Size.Medium)

    val creatureSecondaryTraits = mutableStateListOf("", "")

    var perceptionTier by mutableStateOf(StatTier.Moderate)

    val perceptionModifier by derivedStateOf {
        perceptionTable[creatureLevel]!![perceptionTier]!!
    }
    val vision = mutableStateOf(VisionType.Normal)
    val perceptionSecondaryTraits = mutableStateListOf(PerceptionSecondaryTrait())

    val creatureLanguages = mutableStateListOf("", "")

    val skillModifiers = StatMap(
        creatureVM = this,
        table = skillTable,
        values = Skill.values()
    )
    //TODO Doesn't update when level is changed

    val abilityModifiers = StatMap(
        creatureVM = this,
        table = abilityModifiersTable,
        values = Ability.values()

    )
    //TODO Make generic function
}