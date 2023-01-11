import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

class ApplicationVM {
    val page = mutableStateOf(Pages.HomePage)
}

class CreatureVM {
    var creatureName by mutableStateOf("")
    var creatureLevel by mutableStateOf(0)

    val creatureRarity = mutableStateOf(Rarity.Common)
    val creatureAlignment = mutableStateOf(Alignment.TN)
    val creatureSize = mutableStateOf(Size.Medium)

    val creatureSecondaryTraits = mutableStateListOf("", "")

    var perceptionTier by mutableStateOf(StatTier.Moderate)

    val perceptionModifier by derivedStateOf {
        perceptionTable[creatureLevel]!![perceptionTier]!!
    }
    val vision = mutableStateOf(VisionType.Normal)
    val perceptionSecondaryTraits = mutableStateListOf(PerceptionSecondaryTrait())

    val creatureLanguages = mutableStateListOf("", "")

    var skillModifiers by mutableStateOf(
        SkillMap(
            creatureVM = this,
            mutableMap = skillTable
        )
    ) //TODO Doesn't update when level is changed

    var abilityModifiers by mutableStateOf(
        AbilityMap(
            creatureVM = this,
            mutableMap = abilityModifiersTable
        )
    ) //TODO Make generic function
}

class SkillMap(var creatureVM: CreatureVM, val mutableMap: MutableMap<Int, Map<StatTier, Int>>) {
    var map = Skill.values()
        .map { it to Pair(mutableMap[creatureVM.creatureLevel]!![StatTier.Moderate]!!, StatTier.Moderate) }
        .toMutableStateMap()

    fun changeToStatTier(skill: Skill, statTier: StatTier) {
        map[skill] = Pair(mutableMap[creatureVM.creatureLevel]!![statTier]!!, statTier)
    }

    fun changeToModValue(skill: Skill, mod: Int) {
        if (mod <= mutableMap[creatureVM.creatureLevel]!![StatTier.Terrible]!!) {
            val tier = StatTier.Terrible
            map[skill] = Pair(mod, tier)
        } else {
            mutableMap[creatureVM.creatureLevel]!!.entries.forEach {
                if (mod >= it.value) {
                    val tier = it.key
                    map[skill] = Pair(mod, tier)
                    /*map.entries.forEach {
                        println(it.key)
                        println(it.value.first)
                        println(it.value.second)
                        println("----")
                    }*/
                    return
                }
            }
        }
    }
}

class AbilityMap(var creatureVM: CreatureVM, val mutableMap: MutableMap<Int, Map<StatTier, Int>>) {
    var map = Ability.values()
        .map { it to Pair(mutableMap[creatureVM.creatureLevel]!![StatTier.Moderate]!!, StatTier.Moderate) }
        .toMutableStateMap()

    fun changeToStatTier(ability: Ability, statTier: StatTier) {
        map[ability] = Pair(mutableMap[creatureVM.creatureLevel]!![statTier]!!, statTier)
    }

    fun changeToModValue(ability: Ability, mod: Int) {
        if (mod <= mutableMap[creatureVM.creatureLevel]!![StatTier.Low]!!) {
            val tier = StatTier.Low
            map[ability] = Pair(mod, tier)
        } else {
            mutableMap[creatureVM.creatureLevel]!!.entries.forEach {
                if (mod >= it.value) {
                    val tier = it.key
                    map[ability] = Pair(mod, tier)
                    /*map.entries.forEach {
                        println(it.key)
                        println(it.value.first)
                        println(it.value.second)
                        println("----")
                    }*/
                    return
                }
            }
        }
    }
}


enum class Ability {
    Strength,
    Dexterity,
    Constitution,
    Intelligence,
    Wisdom,
    Charisma
}

enum class Skill {
    Acrobatics,
    Arcana,
    Athletics,
    Crafting,
    Deception,
    Diplomacy,
    Intimidation,
    Medicine,
    Nature,
    Occultism,
    Performance,
    Religion,
    Society,
    Stealth,
    Survival,
    Thievery
}

interface ColorDropdownItem : DropdownItem {
    val color: Color
}

interface DropdownItem {
    val name: String
}

interface SpecialNameDropdownItem : DropdownItem {
    val altName: String
}

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


