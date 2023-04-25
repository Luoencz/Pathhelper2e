package data

import androidx.compose.runtime.*

sealed interface CreatureCharacteristic {
    var name: MutableState<String>
    data class GeneralTrait(
        override var name: MutableState<String>,
        var description: MutableState<String>
    ) : CreatureCharacteristic

    data class PerceptionSense(
        override var name: MutableState<String>,
        var range: MutableState<Int>,
        var precision: MutableState<SensePrecision>,
        var description: MutableState<String>
    ) : CreatureCharacteristic

    data class Stat(
        override var name: MutableState<String>,
        var stat: MutableState<models.Stat>,
        var description: MutableState<String>
    ) : CreatureCharacteristic

    data class Resistance(
        override var name: MutableState<String>,
        var value: Int,
        var damageType: String,
        var description: MutableState<String>
    ) : CreatureCharacteristic
}