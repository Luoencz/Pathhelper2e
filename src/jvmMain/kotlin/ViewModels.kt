import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
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
            calculateSkillModifier(skill.keyAbility, proficiency)
        }
    }

    private fun calculateSkillModifier(keyAbility: Ability, proficiency: Proficiency) =
        abilityScores[keyAbility]!!.modifier + (creatureLevel.value * proficiency.levelMultiplier) + proficiency.addition

    val abilityScores = mapOf(
        Ability.Strength to AbilityScore(10),
        Ability.Dexterity to AbilityScore(10),
        Ability.Constitution to AbilityScore(10),
        Ability.Intelligence to AbilityScore(10),
        Ability.Wisdom to AbilityScore(10),
        Ability.Charisma to AbilityScore(10)
    )
}

enum class Ability() {
    Strength,
    Dexterity,
    Constitution,
    Intelligence,
    Wisdom,
    Charisma
}

enum class Proficiency(override val color: Color, val levelMultiplier: Int, val addition: Int) : ColorDropdownItem {
    Untrained(Color.Transparent, 0, 0),
    Trained(Color.White, 1, 2),
    Expert(Color.Yellow, 1, 4),
    Master(Color.Cyan, 1, 6),
    Legendary(Color.Magenta, 1, 8)
}

class AbilityScore(score: Int) {
    var score by mutableStateOf(score, neverEqualPolicy())

    val modifier by derivedStateOf {
        floor((this.score - 10) / 2.0).toInt()
    }
}

enum class Skill(
    val title: String,
    val keyAbility: Ability
) {
    Acrobatics("Acrobatics", Ability.Dexterity),
    Arcana("Arcana", Ability.Intelligence),
    Athletics("Athletics", Ability.Strength),
    Crafting("Crafting", Ability.Intelligence),
    Deception("Deception", Ability.Charisma),
    Diplomacy("Diplomacy", Ability.Charisma),
    Intimidation("Intimidation", Ability.Charisma),
    Medicine("Medicine", Ability.Wisdom),
    Nature("Nature", Ability.Wisdom),
    Occultism("Occultism", Ability.Intelligence),
    Performance("Performance", Ability.Charisma),
    Religion("Religion", Ability.Wisdom),
    Society("Society", Ability.Charisma),
    Stealth("Stealth", Ability.Dexterity),
    Survival("Survival", Ability.Wisdom),
    Thievery("Thievery", Ability.Dexterity)

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
