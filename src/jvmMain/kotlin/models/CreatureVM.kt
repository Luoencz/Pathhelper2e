package models


import data.AC
import data.Alignment
import data.Perception
import data.Rarity
import data.SavingThrow
import data.Size
import androidx.compose.runtime.*
import data.*

class CreatureVM {
    var name by mutableStateOf("Nameless Beast")
    var level by mutableStateOf(0)

    val rarity = mutableStateOf(Rarity.Common)
    val alignment = mutableStateOf(Alignment.TN)
    val size = mutableStateOf(Size.Medium)

    val speed = mutableStateOf(25)

    val abilityCharacteristics = listOf(
        CreatureCharacteristic.Ability(mutableStateOf("Strength"), mutableStateOf(Stat(abilityModifiersTable, mutableStateOf(level))), mutableStateOf("")),
        CreatureCharacteristic.Ability(mutableStateOf("Dexterity"), mutableStateOf(Stat(abilityModifiersTable, mutableStateOf(level))), mutableStateOf("")),
        CreatureCharacteristic.Ability(mutableStateOf("Constitution"), mutableStateOf(Stat(abilityModifiersTable, mutableStateOf(level))), mutableStateOf("")),
        CreatureCharacteristic.Ability(mutableStateOf("Wisdom"), mutableStateOf(Stat(abilityModifiersTable, mutableStateOf(level))), mutableStateOf("")),
        CreatureCharacteristic.Ability(mutableStateOf("Intelligence"), mutableStateOf(Stat(abilityModifiersTable, mutableStateOf(level))), mutableStateOf("")),
        CreatureCharacteristic.Ability(mutableStateOf("Charisma"), mutableStateOf(Stat(abilityModifiersTable, mutableStateOf(level))), mutableStateOf("")),
    )
    val perceptionCharacteristic = CreatureCharacteristic.Ability(mutableStateOf("Perception"), mutableStateOf(Stat(perceptionTable, mutableStateOf(level))), mutableStateOf(""))

    val defenseCharacteristics = listOf(
        CreatureCharacteristic.Ability(mutableStateOf("HP"), mutableStateOf(Stat(hpTable, mutableStateOf(level))), mutableStateOf("")),
        CreatureCharacteristic.Ability(mutableStateOf("AC"), mutableStateOf(Stat(acModifiersTable, mutableStateOf(level))), mutableStateOf("")),
        CreatureCharacteristic.Ability(mutableStateOf("Reflex"), mutableStateOf(Stat(savingThrowsTable, mutableStateOf(level))), mutableStateOf("")),
        CreatureCharacteristic.Ability(mutableStateOf("Will"), mutableStateOf(Stat(savingThrowsTable, mutableStateOf(level))), mutableStateOf("")),
        CreatureCharacteristic.Ability(mutableStateOf("Fortitude"), mutableStateOf(Stat(savingThrowsTable, mutableStateOf(level))), mutableStateOf("")),
    )

    val creatureCharacteristics = mutableStateListOf<MutableState<CreatureCharacteristic>>(
        mutableStateOf(CreatureCharacteristic.GeneralTrait(
            mutableStateOf("Undead"), description = mutableStateOf("")
        )), mutableStateOf(CreatureCharacteristic.GeneralTrait(
            mutableStateOf("Dragon"), description = mutableStateOf("")
        )), mutableStateOf(CreatureCharacteristic.GeneralTrait(
            mutableStateOf("Knight"),description = mutableStateOf("")
        )))
}