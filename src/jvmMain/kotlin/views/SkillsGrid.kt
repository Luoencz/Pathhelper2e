package views

import models.CreatureVM
import data.Skill
import StatTier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.DropdownWithColor

@Composable
fun SkillsGrid(creatureVM: CreatureVM, modifier: Modifier = Modifier) {
    val items = Skill.values()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        modifier
    ) {
        items(items) { skill ->
            Column {
                val mod = creatureVM.skillModifiers.modByStat(skill)
                println("Recomposed with $mod")
                Text(
                    skill.name + ": " + if (mod >= 0) "+$mod" else "-$mod",
                    modifier = Modifier.absolutePadding(top = 5.dp)
                )
                DropdownWithColor(creatureVM.skillModifiers.tierByStat(skill), {
                    creatureVM.skillModifiers.changeToStatTier(skill, it)
                }, arrayOf(StatTier.Extreme, StatTier.High, StatTier.Moderate, StatTier.Low))
            }
        }
    }
}