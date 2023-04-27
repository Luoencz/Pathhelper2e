package data

import androidx.compose.runtime.*

sealed interface CreatureCharacteristic {
    data class GeneralTrait(
        var name: MutableState<String>,
        var description: MutableState<String>
    ) : CreatureCharacteristic

    data class PerceptionSense(
        var name: MutableState<String>,
        var range: MutableState<Int>,
        var precision: MutableState<SensePrecision>,
        var description: MutableState<String>
    ) : CreatureCharacteristic

    data class Stat(
        var name: MutableState<String>,
        var stat: MutableState<models.Stat>,
        var description: MutableState<String>
    ) : CreatureCharacteristic

    data class DamageModifier(
        var value: MutableState<Int>,
        var damageType: MutableState<String>,
        var modifierType: MutableState<DamageModifierType>,
        var description: MutableState<String>
    ) : CreatureCharacteristic
}