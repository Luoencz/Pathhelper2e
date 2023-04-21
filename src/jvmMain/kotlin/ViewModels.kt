import androidx.compose.runtime.*
import data.*
import models.ApplicationVM
import pages.*

class PerceptionTrait(
    name: String = "",
    range: Int = 0,
    sensePrecision: SensePrecision = SensePrecision.Precise
) {
    var range by mutableStateOf(range)
    var sensePrecision by mutableStateOf(sensePrecision)
    var name by mutableStateOf(name)
}

class ReceivedDamageModifierTrait(
    name: String = "",
    traitValue: Int = 0,
    receivedDamageModifierType: ReceivedDamageModifierType = ReceivedDamageModifierType.Resistance
) {
    var traitValue by mutableStateOf(traitValue)
        //if (receivedDamageModifierType == ReceivedDamageModifierType.Immunity) mutableStateOf(null) else mutableStateOf(traitValue)
    var receivedDamageModifierType by mutableStateOf(receivedDamageModifierType)
    var name by mutableStateOf(name)
}


enum class ReceivedDamageModifierType {
    Immunity, Resistance, Weakness
}

enum class Pages {
    HomePage, CreatureMainStatsPage, CreatureAbilitiesAndActionsPage
}

@Composable
fun navigate(applicationVM: ApplicationVM) {
    when (applicationVM.page.value) {
        Pages.HomePage -> homePage(applicationVM)
        Pages.CreatureMainStatsPage -> creatureMainStats(applicationVM)
        else -> {}
    }
}


