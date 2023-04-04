import androidx.compose.runtime.*
import data.*
import models.ApplicationVM
import pages.*

class PerceptionTrait(
    override var name: String = "",
    range: Int = 0,
    sensePrecision: SensePrecision = SensePrecision.Precise
): NamedObject {
    var range by mutableStateOf(range)
    var sensePrecision by mutableStateOf(sensePrecision)
}

class ReceivedDamageModifierTrait(
    override var name: String = "",
    traitValue: Int = 0,
    receivedDamageModifierType: ReceivedDamageModifierType = ReceivedDamageModifierType.Resistance
) : NamedObject {
    var traitValue by mutableStateOf(traitValue)
        //if (receivedDamageModifierType == ReceivedDamageModifierType.Immunity) mutableStateOf(null) else mutableStateOf(traitValue)
    var receivedDamageModifierType by mutableStateOf(receivedDamageModifierType)
}

class BasicNamedObject(
    override var name: String
): NamedObject

enum class ReceivedDamageModifierType: NamedObject {
    Immunity, Resistance, Weakness
}

enum class Pages {
    HomePage, CreatureMainStatsPage, CreatureAbilitiesAndActionsPage
}

@Composable
fun navigate(applicationVM: ApplicationVM) {
    when (applicationVM.page.value) {
        Pages.CreatureAbilitiesAndActionsPage -> creatureAbilitiesAndActions(applicationVM)
        Pages.HomePage -> homePage(applicationVM)
        Pages.CreatureMainStatsPage -> creatureMainStats(applicationVM)
    }
}


