package models

import AC
import Alignment
import PerceptionSecondaryTrait
import Rarity
import SavingThrow
import Size
import StatTier
import VisionType
import androidx.compose.runtime.*
import data.*

class CreatureVM {
    var creatureName by mutableStateOf("")
    var creatureLevel by mutableStateOf(0)

    val creatureRarity = mutableStateOf(Rarity.Common)
    val creatureAlignment = mutableStateOf(Alignment.TN)
    val creatureSize = mutableStateOf(Size.Medium)

    val creatureHP = StatMap(
        creatureVM = this,
        table = hpTable,
        values = HP.values()
    )

    val creatureAC = StatMap(
        creatureVM = this,
        table = acModifiersTable,
        values = AC.values()
    )

    val creatureSavingThrows = StatMap(
        creatureVM = this,
        table = savingThrowsTable,
        values = SavingThrow.values()
    )

    var creatureSecondaryTraits = mutableStateListOf("")

    var perceptionTier by mutableStateOf(StatTier.Moderate)

    val perceptionModifier by derivedStateOf {
        perceptionTable[creatureLevel]!![perceptionTier]!!
    }
    val vision = mutableStateOf(VisionType.Normal)
    val perceptionSecondaryTraits = mutableStateListOf(PerceptionSecondaryTrait())

    val creatureLanguages = mutableStateListOf("", "")

    val skills = mutableStateListOf(Skill.Arcana)
    val skillModifiers = StatMap(
        creatureVM = this,
        table = skillTable,
        values = emptyArray<Skill>()
    )
    //TODO Doesn't update when level is changed

    val abilityModifiers = StatMap(
        creatureVM = this,
        table = abilityModifiersTable,
        values = Ability.values()
    )
    //TODO Make generic function
}