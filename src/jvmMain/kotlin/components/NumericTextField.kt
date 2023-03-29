package components

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*

@Composable
fun NumericTextField(
    value: Int,
    label: String,
    onIntValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    explicitlySigned: Boolean = false
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

    OutlinedTextField(
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
        textStyle = textStyle.copy(fontSize = 20.sp),
        isError = errorState,
        label = { Text(text = label) },
        singleLine = true
    )
}