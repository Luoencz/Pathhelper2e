package data

import androidx.compose.ui.graphics.*

interface NamedObject {
    var name: String
}

interface ColoredNamedObject : NamedObject {
    val color: Color
}

interface SpecialNameNamedObject : NamedObject {
    val altName: String
}