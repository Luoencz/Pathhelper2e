package views

import models.CreatureVM
import data.Skill
import StatTier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.*
import kotlinx.coroutines.flow.*

@Composable
fun SkillsGrid(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    val items = Skill.values()

    HorizontalFlow(modifier) {
        items.forEach { skill ->
            Column(Modifier.padding(end = 5.dp)) {
                val mod = creatureVM.skillModifiers.modByStat(skill)
                Text(
                    skill.name + ": " + if (mod >= 0) "+$mod" else "-$mod",
                )
                DropdownWithColor(creatureVM.skillModifiers.tierByStat(skill), {
                    creatureVM.skillModifiers.changeToStatTier(skill, it)
                }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low))
            }
        }
    }
}