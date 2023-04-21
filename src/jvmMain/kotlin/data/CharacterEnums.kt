package data

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

enum class Alignment{
    LG, NG, CG, LN, NN, TN, CN, LE,
    NE, CE
}

enum class Size {
    Tiny, Small, Medium, Large, Huge, Gargantuan
}

enum class Rarity {
    Common, Uncommon, Rare, Unique
}

enum class SavingThrow {
    Reflex, Will, Fortitude
}

enum class SensePrecision {
    Precise,
    Imprecise,
    Vague
}

enum class StatTier {
    Extreme, High, Moderate, Low, Terrible
}