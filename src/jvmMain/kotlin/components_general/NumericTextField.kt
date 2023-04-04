package components_general

import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NumericTextField(
    value: Int,
    onIntValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    explicitlySigned: Boolean = false,
    label: @Composable () -> Unit
) {
    var focused by mutableStateOf(false)

    var currentValue by remember(value) {
        mutableStateOf(value.toString())
    }

    var errorState by remember {
        mutableStateOf(false)
    }

    if (currentValue.toIntOrNull() != null && currentValue[0] != '+' && currentValue.toInt() >= 0 && explicitlySigned && !focused) {
        currentValue = "+$currentValue"
    }
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = currentValue,
        modifier = modifier.onFocusChanged { focus ->
                    focused = focus.isFocused
                   if (!focused)  {
                       if (errorState) {
                           errorState = false
                           currentValue = value.toString()
                       }
                   }
        },
        onValueChange = {
            currentValue = it
            when (val intValue = it.toIntOrNull()) {
                null -> {
                    errorState = true
                }

                else -> {
                    errorState = false
                    onIntValueChange(intValue)
                }
            }
        },
        interactionSource = interactionSource,
        textStyle = textStyle.copy(fontSize = 20.sp, textAlign = TextAlign.Center),
        singleLine = true,
    ) {
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = currentValue,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = true,
            enabled = true,
            label = label,
            isError = errorState,
            interactionSource = interactionSource,
            //trailingIcon = trailingIcon,
            // keep vertical paddings but change the horizontal
            contentPadding = TextFieldDefaults.outlinedTextFieldPadding(
                start = 0.dp, end = 0.dp, top = 4.dp, bottom = 4.dp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )
    }
}