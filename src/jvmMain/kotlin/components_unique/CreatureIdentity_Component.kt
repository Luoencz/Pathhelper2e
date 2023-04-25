package components_unique

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import data.*
import models.*

val impact: FontFamily = FontFamily.Default

@Composable
fun Identity(creatureVM: CreatureVM) {
    var popupControl by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(15.dp)) {
        Box(
            modifier = Modifier
                .requiredWidth(200.0.dp)
                .requiredHeight(200.0.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
                    .background(LightBackgroundColor)
            ) {
                Image(
                    painter = painterResource("main_frame_picture.png"),
                    contentDescription = "Creature Image",
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.dp, y = (-13).dp)
                    .requiredWidth(140.0.dp)
                    .requiredHeight(25.0.dp)
                    .background(LightBackgroundColor)
                    .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(3.dp),
                contentAlignment = Alignment.Center
            ) {
                val interactionSource = remember { MutableInteractionSource() }
                BasicTextField(
                    value = creatureVM.name,
                    onValueChange = { creatureVM.name = it },
                    interactionSource = interactionSource,
                    textStyle = InteractiveTextStyle.merge(TextStyle(textAlign = TextAlign.Center)),
                    singleLine = true
                )
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .offset(x = 0.dp, y = 13.dp)
                    .requiredWidth(66.0.dp)
                    .requiredHeight(24.0.dp)
                    .background(LightBackgroundColor)
                    .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
                    .padding(3.dp),
                contentAlignment = Alignment.Center
            ) {
                BasicText(
                    "Level: ${
                        creatureVM
                            .level
                    }",
                    modifier = Modifier.clickable { popupControl = !popupControl },
                    style = InteractiveTextStyle.merge(TextStyle(textAlign = TextAlign.Center)),
                )
            }
        }
        Box {
            if (popupControl) {
                Popup(alignment = Alignment.TopCenter, focusable = true, onDismissRequest = { popupControl = false }) {
                    Column(
                        Modifier
                            .background(BackgroundColor)
                    ) {
                        BasicText(
                            "Choose level of the creature!",
                            style = BoldTextStyle.merge(TextStyle(fontSize = 17.sp)),
                            modifier = Modifier.padding(5.dp).width(190.dp)
                        )
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(40.dp),
                            contentPadding = PaddingValues(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier.width(200.dp)
                        ) {
                            items(26) {
                                Box(modifier = Modifier
                                    .clickable {
                                    creatureVM.level = it - 1
                                    popupControl = false }
                                    .border(1.dp, InteractiveColor, RoundedCornerShape(CornerSize(3.dp)))
                                    .background(LightBackgroundColor)
                                    .size(40.dp),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    BasicText(
                                        (it - 1).toString(),
                                        style = BoldTextStyle.merge(TextStyle(fontSize = 25.sp)),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

