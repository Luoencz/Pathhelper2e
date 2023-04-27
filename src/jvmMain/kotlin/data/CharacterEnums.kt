package data

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

enum class SensePrecision {
    Precise,
    Imprecise,
    Vague
}

enum class StatTier {
    Extreme, High, Moderate, Low, Terrible
}

enum class DamageModifierType {
    Immunity, Weakness, Resistance
}