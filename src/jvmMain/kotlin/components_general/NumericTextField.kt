package components_general

import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import com.google.relay.compose.*
import data.*

@Composable
fun NumericTextField(
    value: Int,
    onIntValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    explicitlySigned: Boolean = false,
    label: String? = null
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

    Box(modifier = modifier) {
        RelayContainer(
            backgroundColor = Color(
                alpha = 255,
                red = 252,
                green = 251,
                blue = 246
            ),
            isStructured = true,
            radius = 4.0,
            strokeWidth = 1.0,
            strokeColor = Color(
                alpha = 255,
                red = 9,
                green = 39,
                blue = 96
            ),
            content = {
                BasicTextField(
                    value = currentValue,
                    modifier = Modifier.onFocusChanged { focus ->
                        println("focus")
                        focused = focus.isFocused
                        if (!focused) {
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
                )
            },
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        )
        if (label != null)
        RelayContainer(
            backgroundColor = Color(
                alpha = 255,
                red = 252,
                green = 251,
                blue = 246
            ),
            isStructured = false,
            radius = 4.0,
            strokeWidth = 0.0,
            strokeColor = Color(
                alpha = 255,
                red = 9,
                green = 39,
                blue = 96
            ),
            content = {
                Text(text = label, textAlign = TextAlign.Center, style = BoldTextStyle, modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .widthIn(max = 45.dp), maxLines = 1
                )
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 5.dp, y = (-7).dp)
        )
    }
}