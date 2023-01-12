package pages

import androidx.compose.material.*
import androidx.compose.runtime.*
import models.*

@Composable
fun creatureAbilitiesAndActions(applicationVM: ApplicationVM) {
    val creatureVM = remember { applicationVM.creatureVM }
    Text(text = creatureVM.creatureName)
}