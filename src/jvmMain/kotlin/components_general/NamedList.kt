package components_general

import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import data.*

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun <T: NamedObject>NamedList(
    modifier: Modifier = Modifier,
    traitsList: SnapshotStateList<T>,
    lambdaConstructor: (String) -> T,
    content: @Composable (NamedObject) -> Unit = {
        NamedBubble(
            trait = it,
            modifier = Modifier.padding(end = 2.dp),
            traitsList = traitsList,
            content = {
                Box {
                    Text(
                        text = it.name,
                        maxLines = 1,
                        modifier = Modifier
                            .widthIn(1.dp, Dp.Infinity)
                            .align(Alignment.Center)
                    )
                }
            }
        )
    },
    label: @Composable () -> Unit = { Text(text = "New Trait") }
) {
    HorizontalFlow(verticalArrangement = Arrangement.Center, modifier = modifier)
    {
        traitsList.forEach {
            content(it)
        }

        var currentValue by remember { mutableStateOf("") }
        val interactionSource = remember { MutableInteractionSource() }
            BasicTextField(
                value = currentValue,
                onValueChange = { currentValue = it },
                interactionSource = interactionSource,
                enabled = true,
                singleLine = true,
                modifier = Modifier
                    .onKeyEvent { keyEvent ->
                        if ((keyEvent.key == Key.Enter || keyEvent.key == Key.Comma) && (keyEvent.type == KeyEventType.KeyUp)) {
                            if (currentValue.filterNot { it.isWhitespace() } != "" && traitsList.find { it.name == currentValue } == null) {
                                traitsList.add(lambdaConstructor(currentValue))
                            }
                            currentValue = ""
                            true
                        } else {
                            false
                        }
                    }
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = currentValue,
                    visualTransformation = VisualTransformation.None,
                    innerTextField = it,
                    singleLine = true,
                    enabled = true,
                    label = label,
                    interactionSource = interactionSource,
                    // keep vertical paddings but change the horizontal
                    contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                        start = 7.dp, end = 7.dp
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors()
                )
            }
    }
}

