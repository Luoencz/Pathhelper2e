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

class GeneralTrait(
    override var name: String
): NamedObject


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


