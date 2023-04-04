package components_unique

import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import models.*

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun Name_Component(creatureVM: CreatureVM) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = creatureVM.creatureName,
        onValueChange = { creatureVM.creatureName = it },
        interactionSource = interactionSource,
        enabled = true,
        singleLine = true,
        modifier = Modifier
            .widthIn(10.dp, Dp.Infinity)
    ) {
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = creatureVM.creatureName,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = true,
            enabled = true,
            label = { Text(text = "Name") },
            interactionSource = interactionSource,
            // keep vertical paddings but change the horizontal
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                start = 8.dp, end = 8.dp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )
    }
}