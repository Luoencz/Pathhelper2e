package components_general

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import data.*

@Composable
fun NumericTextField(
    value: Int,
    onIntValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    explicitlySigned: Boolean = false,
    label: String? = null,
) {
    var focused by mutableStateOf(false)
    var labelCoordinates by remember { mutableStateOf<Rect?>(null) }

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

    Box {
        Box(
            modifier = modifier
                .background(LightBackgroundColor)
                .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp))),
            contentAlignment = Alignment.Center
        ) {
            Row {
                BasicTextField(
                    value = currentValue,
                    modifier = Modifier
                        .onFocusChanged { focus ->
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
            }
        }
        if (label != null)
            Box(Modifier.align(Alignment.TopStart).offset(x = 5.dp, y = (-7).dp).background(LightBackgroundColor)
                ) {
                    Text(
                        text = label, textAlign = TextAlign.Center, style = BoldTextStyle, modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .widthIn(max = 45.dp)
                            .onGloballyPositioned {
                                labelCoordinates = it.boundsInParent()
                            },
                        maxLines = 1
                    )
            }
    }
}