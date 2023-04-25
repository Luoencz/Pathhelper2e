package models

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import data.*

sealed interface StatSetup {
    data class Modifier(val value: Int) : StatSetup

    data class Tier(val tier: StatTier) : StatSetup
}

class Stat(val table: MutableMap<Int, Map<StatTier, Int>>, val level: MutableState<Int>) {
    var statSetup: StatSetup by mutableStateOf(StatSetup.Tier(StatTier.Moderate))

    fun modByStat(): Int {
        return when (statSetup) {
            is StatSetup.Modifier -> (statSetup as StatSetup.Modifier).value
            is StatSetup.Tier -> table[level.value]!![(statSetup as StatSetup.Tier).tier]!!
        }
    }

    fun tierByStat(): StatTier {
        val tiers = table[0]!!.keys
        return when (statSetup) {
            is StatSetup.Modifier -> tiers.firstOrNull {
                table[level.value]!![it]!! <= (statSetup as StatSetup.Modifier).value
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