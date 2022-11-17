import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import kotlin.math.floor

class ApplicationVM {
    val page = mutableStateOf(Pages.HomePage)
}

class CreatureVM {
    val creatureName = mutableStateOf("")
    val creatureLevel = mutableStateOf(0)

    val creatureRarity = mutableStateOf(Rarities.Common)
    val creatureAlignment = mutableStateOf(Alignment.TN)
    val creatureSize = mutableStateOf(Size.Medium)
    val creatureSecondaryTraits = mutableStateListOf("", "")

    val proficiencies = mutableStateMapOf<Skill, Proficiency>().apply {
        Skill.values().forEach {
            put(it, Proficiency.Untrained)
        }
    }

    val skillModifiers by derivedStateOf {
        proficiencies.mapValues { skillProficiency ->
            val (skill, proficiency) = skillProficiency
            when (skill) {
                Skill.Acrobatics -> abilityScores.first { it.name == "Dexterity" }.modifier
                Skill.Arcana -> 0
                Skill.Athletics -> 0
                Skill.Crafting -> 0
                Skill.Deception -> 0
                Skill.Diplomacy -> 0
                Skill.Intimidation -> 0
                Skill.Medicine -> 0
                Skill.Nature -> 0
                Skill.Occultism -> 0
                Skill.Performance -> 0
                Skill.Religion -> 0
                Skill.Society -> 0
                Skill.Stealth -> 0
                Skill.Survival -> 0
                Skill.Thievery -> 0
            }
        }
    }

    val abilityScores = listOf(
        AbilityScore(10, "Strength"),
        AbilityScore(10, "Dexterity"),
        AbilityScore(10, "Constitution"),
        AbilityScore(10, "Intelligence"),
        AbilityScore(10, "Wisdom"),
        AbilityScore(10, "Charisma")
    )

}

enum class Proficiency(override val color: Color) : ColorDropdownItem {
    Untrained(Color.Transparent),
    Trained(Color.White),
    Expert(Color.Yellow),
    Master(Color.Cyan),
    Legendary(Color.Magenta)
}

class AbilityScore(score: Int, val name: String) {
    var score by mutableStateOf(score, neverEqualPolicy())

    val modifier by derivedStateOf {
        floor((this.score - 10) / 2.0).toInt()
    }
}

enum class Skill(
    val title: String
) {
    Acrobatics("Acrobatics"), Arcana("Arcana"), Athletics("Athletics"), Crafting("Crafting"), Deception("Deception"), Diplomacy(
        "Diplomacy"
    ),
    Intimidation("Intimidation"), Medicine("Medicine"), Nature("Nature"), Occultism("Occultism"), Performance("Performance"), Religion(
        "Religion"
    ),
    Society("Society"), Stealth("Stealth"), Survival("Survival"), Thievery("Thievery")

}

interface ColorDropdownItem {
    val color: Color
    val name: String
}

enum class Alignment(override val color: Color) : ColorDropdownItem {
    LG(Color.Green), NG(Color.Green), CG(Color.Green), LN(Color.Green), NN(Color.Green), TN(Color.Green), CN(Color.Green), LE(
        Color.Green
    ),
    NE(Color.Green), CE(Color.Green)
}

enum class Size(override val color: Color) : ColorDropdownItem {
    Tiny(Color.LightGray), Small(Color.LightGray), Medium(Color.LightGray), Large(Color.LightGray), Huge(Color.LightGray), Gargantuan(
        Color.LightGray
    )
}

enum class Rarities(override val color: Color) : ColorDropdownItem {
    Common(Color.White), Uncommon(Color.Yellow), Rare(Color.Cyan), Unique(Color.Magenta)
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
