package models


import androidx.compose.runtime.*
import data.*

class CreatureVM {
    var name by mutableStateOf("Nameless Beast")
    var level = mutableStateOf(0)

    val rarity = mutableStateOf(Rarity.Common)
    val alignment = mutableStateOf(Alignment.TN)
    val size = mutableStateOf(Size.Medium)

    val speed = mutableStateOf(25)

    val abilityCharacteristics = listOf(
        CreatureCharacteristic.Stat(
            mutableStateOf("Strength"),
            mutableStateOf(Stat(abilitiesTable, level)),
            mutableStateOf("")
        ),
        CreatureCharacteristic.Stat(
            mutableStateOf("Dexterity"),
            mutableStateOf(Stat(abilitiesTable, level)),
            mutableStateOf("")
        ),
        CreatureCharacteristic.Stat(
            mutableStateOf("Constitution"),
            mutableStateOf(Stat(abilitiesTable, level)),
            mutableStateOf("")
        ),
        CreatureCharacteristic.Stat(
            mutableStateOf("Wisdom"),
            mutableStateOf(Stat(abilitiesTable, level)),
            mutableStateOf("")
        ),
        CreatureCharacteristic.Stat(
            mutableStateOf("Intelligence"),
            mutableStateOf(Stat(abilitiesTable, level)),
            mutableStateOf("")
        ),
        CreatureCharacteristic.Stat(
            mutableStateOf("Charisma"),
            mutableStateOf(Stat(abilitiesTable, level)),
            mutableStateOf("")
        ),
    )
    val perceptionCharacteristic = CreatureCharacteristic.Stat(
        mutableStateOf("Perception"),
        mutableStateOf(Stat(perceptionTable, level)),
        mutableStateOf("")
    )

    val defenseCharacteristics = listOf(
        CreatureCharacteristic.Stat(
            mutableStateOf("HP"),
            mutableStateOf(Stat(hpTable, level)),
            mutableStateOf("")
        ),
        CreatureCharacteristic.Stat(
            mutableStateOf("AC"),
            mutableStateOf(Stat(acTable, level)),
            mutableStateOf("")
        ),
        CreatureCharacteristic.Stat(
            mutableStateOf("Reflex"),
            mutableStateOf(Stat(savingThrowsTable, level)),
            mutableStateOf("")
        ),
        CreatureCharacteristic.Stat(
            mutableStateOf("Will"),
            mutableStateOf(Stat(savingThrowsTable, level)),
            mutableStateOf("")
        ),
        CreatureCharacteristic.Stat(
            mutableStateOf("Fortitude"),
            mutableStateOf(Stat(savingThrowsTable, level)),
            mutableStateOf("")
        ),
    )

    val creatureCharacteristics = mutableStateListOf<MutableState<CreatureCharacteristic>>(
        mutableStateOf(
            CreatureCharacteristic.PerceptionSense(
                mutableStateOf("Dark Vision"),
                precision = mutableStateOf(SensePrecision.Precise),
                range = mutableStateOf(100),
                description = mutableStateOf("")
            )
        ), mutableStateOf(
            CreatureCharacteristic.GeneralTrait(
                name = mutableStateOf("Dragon"),
                description = mutableStateOf("")
            )
        ), mutableStateOf(
            CreatureCharacteristic.Stat(
                name = mutableStateOf("Dragon Lore"),
                stat = mutableStateOf(Stat(skillTable, level)),
                description = mutableStateOf("")
            )
        ),
        mutableStateOf(
            CreatureCharacteristic.DamageModifier(
                value = mutableStateOf(1),
                damageType = mutableStateOf("Fire"),
                modifierType = mutableStateOf(DamageModifierType.Resistance),
                description = mutableStateOf("")
            )
        )
    )
}