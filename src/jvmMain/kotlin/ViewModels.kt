import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

class ApplicationVM {
    val page = mutableStateOf(Pages.HomePage)
}

class CreatureVM {
    val creatureName = mutableStateOf(TextFieldValue(""))

    val creatureRarity = mutableStateOf(Rarities.Common)
    val creatureAlignment = mutableStateOf(Alignment.TN)
    val creatureSize = mutableStateOf(Size.Medium)
    val creatureSecondaryTraits = mutableStateListOf("")

    val proficiencies = mutableStateMapOf<Skill, Proficiency>()
    val abilityScores = mutableStateOf(CreatureAbilityScores())
}

enum class Proficiency(override val color: Color) : ColorDropdownItem {
    Untrained(Color.Transparent),
    Trained(Color.White),
    Expert(Color.Yellow),
    Master(Color.Cyan),
    Legendary(Color.Magenta)

}


class AbilityScore(Score: Int,Name: String) {
    val score = mutableStateOf(Score)

    val modifier = derivedStateOf {
        (score.value - 10) / 2
    }
    val name = Name
}

class CreatureAbilityScores() {
    val strength = AbilityScore(10,"Strength")
    val dexterity = AbilityScore(10, "Dexterity")
    val constitution = AbilityScore(10, "Constitution")
    val intelligence = AbilityScore(10, "Intelligence")
    val wisdom = AbilityScore(10, "Wisdom")
    val charisma = AbilityScore(10, "Charisma")

    fun list_ability_scores(): List<AbilityScore> {
        return mutableListOf(strength,dexterity,constitution,intelligence,wisdom,charisma)
    }
}

enum class Skill(
    val title: String
) {
    Acrobatics("Acrobatics"),
    Arcana("Arcana"),
    Athletics("Athletics"),
    Crafting("Crafting"),
    Deception("Deception"),
    Diplomacy("Diplomacy"),
    Intimidation("Intimidation"),
    Medicine("Medicine"),
    Nature("Nature"),
    Occultism("Occultism"),
    Performance("Performance"),
    Religion("Religion"),
    Society("Society"),
    Stealth("Stealth"),
    Survival("Survival"),
    Thievery("Thievery")

}

interface ColorDropdownItem {
    val color: Color
    val name: String
}

enum class Alignment(override val color: Color) : ColorDropdownItem {
    LG(Color.Green),
    NG(Color.Green),
    CG(Color.Green),
    LN(Color.Green),
    NN(Color.Green),
    TN(Color.Green),
    CN(Color.Green),
    LE(Color.Green),
    NE(Color.Green),
    CE(Color.Green)
}

enum class Size(override val color: Color) : ColorDropdownItem {
    Tiny(Color.LightGray),
    Small(Color.LightGray),
    Medium(Color.LightGray),
    Large(Color.LightGray),
    Huge(Color.LightGray),
    Gargantuan(Color.LightGray)
}

enum class Rarities(override val color: Color) : ColorDropdownItem {
    Common(Color.White),
    Uncommon(Color.Yellow),
    Rare(Color.Cyan),
    Unique(Color.Magenta)
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
