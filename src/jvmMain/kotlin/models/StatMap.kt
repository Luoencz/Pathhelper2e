package models

import data.StatTier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap

sealed interface StatSetup {
    data class Modifier(val value: Int) : StatSetup

    data class Tier(val tier: StatTier) : StatSetup
}

class StatMap<T:Enum<T>>(val creatureVM: CreatureVM, val table: MutableMap<Int, Map<StatTier, Int>>, values: Array<T>) {
    var setups: SnapshotStateMap<T, StatSetup> =
        values.map { it to StatSetup.Tier(StatTier.Moderate) }.toMutableStateMap()

    fun modByStat(key: T): Int {
        val setup = setups[key] ?: StatSetup.Tier(StatTier.Moderate)
        return when (setup) {
            is StatSetup.Modifier -> setup.value
            is StatSetup.Tier -> table[creatureVM.creatureLevel]!![setup.tier]!!
        }
    }

    fun tierByStat(key: T): StatTier {
        val setup = setups[key] ?: StatSetup.Tier(StatTier.Moderate)
        val tiers = table[0]!!.keys
        return when (setup) {
            is StatSetup.Modifier -> tiers.firstOrNull {
                table[creatureVM.creatureLevel]!![it]!! <= setup.value
            } ?: StatTier.values()[table[0]!!.values.size - 1] //Chooses the last StatTier of the table as a default/min option to avoid outOfBounds

            is StatSetup.Tier -> setup.tier
        }
    }

    fun changeToStatTier(key: T, statTier: StatTier) {
        setups[key] = StatSetup.Tier(statTier)
    }

    fun changeToMod(key: T, mod: Int) {
        setups[key] = StatSetup.Modifier(mod)
    }
}