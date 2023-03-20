package views

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.*
import androidx.compose.ui.text.input.*
import models.*

@Composable
fun SecondaryTraitsComponentV2(creatureVM: CreatureVM){
    OutlinedTextField(value = creatureVM.creatureSecondaryTraits.joinToString(","), onValueChange = {
        creatureVM.creatureSecondaryTraits = it.split(",").toMutableStateList()
        creatureVM.creatureSecondaryTraits.forEach { println(it) }
    })
}

//Undead, AAsimar, Bla-bla