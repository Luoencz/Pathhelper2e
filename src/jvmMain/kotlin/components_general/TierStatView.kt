package components_general

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import data.*
import models.*

@Composable
fun TierStatView(
    modifier: Modifier = Modifier,
    stat: Stat,
    label: String? = null,
    explicitlySigned: Boolean = false
) {
    Box(
        modifier = modifier,
    ) {
        NumericTextField(
            value = stat.modByStat(),
            onIntValueChange = { stat.changeToMod(it) },
            modifier = Modifier
                .width(60.dp)
                .height(35.dp)
                .align(Alignment.Center),
            textStyle = when (stat.statSetup) {
                is StatSetup.Modifier -> BoldTextStyle
                null, is StatSetup.Tier -> RegularTextStyle
            },
            explicitlySigned = explicitlySigned,
            label = label
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .absoluteOffset(x = 10.0.dp)
                .background(
                    Color(
                        alpha = 255,
                        red = 252,
                        green = 251,
                        blue = 246
                    )
                )
                .border(
                    width = 1.dp, color = Color(
                        alpha = 255,
                        red = 9,
                        green = 39,
                        blue = 96
                    ), shape = RoundedCornerShape(CornerSize(3.dp))
                ),
            contentAlignment = Alignment.Center
        ) {
            TextDropdown(
                stat.tierByStat(), {
                    stat.changeToStatTier(it)
                },
                stat.table[0]!!.keys.toTypedArray(),
                Modifier,
                true
            )
        }
    }
}