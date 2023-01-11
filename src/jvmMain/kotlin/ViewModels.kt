import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import components.ColorDropdownItem
import components.DropdownItem
import components.SpecialNameDropdownItem
import models.ApplicationVM
import models.CreatureVM




enum class Alignment : DropdownItem {
    LG, NG, CG, LN, NN, TN, CN, LE,
    NE, CE
}

enum class Size : DropdownItem {
    Tiny, Small, Medium, Large, Huge, Gargantuan
}

enum class Rarity(override val color: Color) : ColorDropdownItem {
    Common(Color.White), Uncommon(Color.Yellow), Rare(Color.Cyan), Unique(Color.Magenta)
}

enum class VisionType(override val altName: String, override val color: Color) : ColorDropdownItem,
    SpecialNameDropdownItem {
    Normal("Normal", Color.White), LowLightVision("Low-light vision", Color.Gray), DarkVision("Darkvision", Color.Cyan)
}

class PerceptionSecondaryTrait(
    name: String = "",
    range: Int = 0,
    sensePrecision: SensePrecision = SensePrecision.Precise
) {
    var name by mutableStateOf(name)
    var range by mutableStateOf(range)
    var sensePrecision by mutableStateOf(sensePrecision)
}

enum class SensePrecision : DropdownItem {
    Precise,
    Imprecise,
    Vague
}


enum class Pages {
    HomePage, CreatureCreatorPage
}

@Composable
fun navigate(applicationVM: ApplicationVM) {
    when (applicationVM.page.value) {
        Pages.HomePage -> homePage(applicationVM)
        Pages.CreatureCreatorPage -> creatureCreatorPage(applicationVM)
    }
}

enum class StatTier : DropdownItem {
    Extreme, High, Moderate, Low, Terrible
}


