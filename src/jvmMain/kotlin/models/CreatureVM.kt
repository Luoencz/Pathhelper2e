package models

import GeneralTrait
import data.AC
import data.Alignment
import data.Perception
import PerceptionTrait
import data.Rarity
import data.SavingThrow
import data.Size
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

    var creatureSecondaryTraits = mutableStateListOf<GeneralTrait>()

    val creaturePerception = StatMap(
        creatureVM = this,
        table = perceptionTable,
        values = Perception.values()
    )

    val perceptionTraits = mutableStateListOf<PerceptionTrait>()

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