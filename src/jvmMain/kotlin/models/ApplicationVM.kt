package models

import Pages
import androidx.compose.runtime.mutableStateOf

class ApplicationVM {
    val page = mutableStateOf(Pages.HomePage)
}