package models

import BasicNamedObject
import data.AC
import data.Alignment
import data.Perception
import PerceptionTrait
import ReceivedDamageModifierTrait
import ReceivedDamageModifierType
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

    var creatureSecondaryTraits = mutableStateListOf<BasicNamedObject>()

    val creatureAC = StatMap(
        creatureVM = this,
        table = acModifiersTable,
        values = AC.values()
    )

    val creatureHP = StatMap(
        creatureVM = this,
        table = hpTable,
        values = HP.values()
    )

    val creatureSavingThrows = StatMap(
        creatureVM = this,
        table = savingThrowsTable,
        values = SavingThrow.values()
    )

    val creaturePerception = StatMap(
        creatureVM = this,
        table = perceptionTable,
        values = Perception.values()
    )
    val perceptionTraits = mutableStateListOf<PerceptionTrait>()

    val creatureLanguages = mutableStateListOf<BasicNamedObject>()

    val creatureReceivedDamageModifiers = mutableStateListOf<ReceivedDamageModifierTrait>()

    val proficientSkills = Skill.values().map { it to false }.toMutableStateMap()
    val skillModifiers = StatMap(
        creatureVM = this,
        table = skillTable,
        values = Skill.values()
    )

    val abilityModifiers = StatMap(
        creatureVM = this,
        table = abilityModifiersTable,
        values = Ability.values()
    )
}