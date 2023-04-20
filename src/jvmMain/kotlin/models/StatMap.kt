package models

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import data.*

sealed interface StatSetup {
    data class Modifier(val value: Int) : StatSetup

    data class Tier(val tier: StatTier) : StatSetup
}

enum class StatType {
    Ability,
    AC,
    HP,
    SavingThrow,
    Perception
}

class Stat(val table: MutableMap<Int, Map<StatTier, Int>>) {
    /*private val table = when(statType) {
        StatType.Ability -> abilityModifiersTable
        StatType.AC -> acModifiersTable
        StatType.HP -> hpTable
        StatType.SavingThrow -> savingThrowsTable
        StatType.Perception -> perceptionTable
    }*/
    var statSetup: StatSetup by mutableStateOf(StatSetup.Tier(StatTier.Moderate))

    fun modByStat(level: Int): Int {
        return when (statSetup) {
            is StatSetup.Modifier -> (statSetup as StatSetup.Modifier).value
            is StatSetup.Tier -> table[level]!![(statSetup as StatSetup.Tier).tier]!!
        }
    }

    fun tierByStat(level: Int): StatTier {
        val tiers = table[0]!!.keys
        return when (statSetup) {
            is StatSetup.Modifier -> tiers.firstOrNull {
                table[level]!![it]!! <= (statSetup as StatSetup.Modifier).value
            } ?: StatTier.values()[table[0]!!.values.size - 1] //Chooses the last StatTier of the table as a default/min option to avoid outOfBounds

            is StatSetup.Tier -> (statSetup as StatSetup.Tier).tier
        }
    }

    fun changeToStatTier(statTier: StatTier) {
        statSetup = StatSetup.Tier(statTier)
    }

    fun changeToMod(mod: Int) {
        statSetup = StatSetup.Modifier(mod)
    }

}

class StatMap<T>(val creatureVM: CreatureVM, val table: MutableMap<Int, Map<StatTier, Int>>, values: Array<T>) {
    var setups: SnapshotStateMap<T, Stat> =
        values.map { it to Stat(table) }.toMutableStateMap()

    fun modByStat(key: T): Int {
        val setup = setups[key] ?: Stat(table)
        return setup.modByStat(creatureVM.creatureLevel)
    }

    fun tierByStat(key: T): StatTier {
        val setup = setups[key] ?: Stat(table)
        return setup.tierByStat(creatureVM.creatureLevel)
    }

    fun changeToStatTier(key: T, statTier: StatTier) {
        setups[key]?.changeToStatTier(statTier)
    }

    fun changeToMod(key: T, mod: Int) {
        setups[key]?.changeToMod(mod)
    }
}