package models


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
import components_unique.CharacteristicType
import components_unique.CreatureCharacteristicCard
import data.*

class CreatureVM {
    var creatureName by mutableStateOf("Nameless Beast")
    var creatureLevel by mutableStateOf(0)

    val creatureRarity = mutableStateOf(Rarity.Common)
    val creatureAlignment = mutableStateOf(Alignment.TN)
    val creatureSize = mutableStateOf(Size.Medium)


    val creatureSpeed = mutableStateOf(25)

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


    val creatureReceivedDamageModifiers = mutableStateListOf<ReceivedDamageModifierTrait>()

    val proficientSkills = Skill.values().map { it to false }.toMutableStateMap()
    val skillModifiers = StatMap(
        creatureVM = this,
        table = skillTable,
        values = Skill.values()
    )

    val knownLore = mutableStateListOf<String>()
    val loreModifiers = StatMap(
        creatureVM = this,
        table = skillTable,
        values = knownLore.toTypedArray()
    )

    val abilityModifiers = StatMap(
        creatureVM = this,
        table = abilityModifiersTable,
        values = Ability.values()
    )

    val CreatureCharacteristics = mutableStateListOf<CreatureCharacteristicCard>(CreatureCharacteristicCard.GeneralTraitCharacteristicCard(
        mutableStateOf("Undead"), type = CharacteristicType.trait, description = ""
    ),CreatureCharacteristicCard.GeneralTraitCharacteristicCard(
        mutableStateOf("Dragon"), type = CharacteristicType.trait, description = ""
    ),CreatureCharacteristicCard.GeneralTraitCharacteristicCard(
        mutableStateOf("Knight"), type = CharacteristicType.trait, description = ""
    ))
}