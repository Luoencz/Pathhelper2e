package models

import Pages
import androidx.compose.runtime.*


class ApplicationVM {
    val page = mutableStateOf(Pages.HomePage)
    val creatureVM = CreatureVM()
}