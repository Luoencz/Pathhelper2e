package data

import androidx.compose.ui.graphics.*

enum class Alignment : NamedObject {
    LG, NG, CG, LN, NN, TN, CN, LE,
    NE, CE
}

enum class Size : NamedObject {
    Tiny, Small, Medium, Large, Huge, Gargantuan
}

enum class Rarity(override val color: Color) : ColoredNamedObject {
    Common(Color.White), Uncommon(Color.Yellow), Rare(Color.Cyan), Unique(Color.Magenta)
}

enum class AC {
    AC
}

enum class HP {
    HP
}

enum class Perception {
    Perception
}

enum class SavingThrow {
    Reflex, Will, Fortitude
}

enum class SensePrecision : NamedObject {
    Precise,
    Imprecise,
    Vague
}

enum class StatTier : NamedObject {
    Extreme, High, Moderate, Low, Terrible
}