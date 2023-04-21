package data

import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.platform.*
import androidx.compose.ui.unit.*

val impactFont = FontFamily(
    Font("fonts/Impact.ttf", FontWeight(400), FontStyle.Normal))
val robotoFont = FontFamily(
    Font("fonts/Roboto-Regular.ttf", FontWeight.Normal, FontStyle.Normal),
    Font("fonts/Roboto-Bold.ttf", FontWeight.Bold, FontStyle.Normal)
)

val TitleColor = Color(
    alpha = 255,
    red = 96,
    green = 27,
    blue = 21
)

val InteractiveColor = Color(
    alpha = 255,
    red = 9,
    green = 39,
    blue = 96
)

val BackgroundColor = Color(
    alpha = 255,
    red = 246,
    green = 243,
    blue = 227
)

val LightBackgroundColor = Color(
    alpha = 255,
    red = 252,
    green = 251,
    blue = 246
)

val TitleTextStyle = TextStyle(
    TitleColor,
    fontSize = 17.sp,
    fontFamily = impactFont
)

val InteractiveTextStyle = TextStyle(
    InteractiveColor,
    fontSize = 15.sp,
    fontFamily = robotoFont
)

val BoldTextStyle = TextStyle(
    Color.Black,
    fontSize = 12.5.sp,
    fontFamily = robotoFont,
    fontWeight = FontWeight.Bold
)

val RegularTextStyle = TextStyle(
    Color.Black,
    fontSize = 12.5.sp,
    fontFamily = robotoFont,
    fontWeight = FontWeight.Normal
)