package pages

import Pages
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components_general.dragAndDropGrid
import components_general.CreatureCharacteristicCard
import models.ApplicationVM

@Composable
fun homePage(applicationVM: ApplicationVM) {
    MaterialTheme {
        Box {
            /*Image(
                painterResource("BackgroundHomePage.png"), "Background_Image",
                Modifier
                    .fillMaxSize(),
                Alignment.Center,
                ContentScale.Crop
            )*/

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                Arrangement.spacedBy(5.dp),
                Alignment.CenterHorizontally
            ) {
                Card(
                    elevation = 4.dp,
                    backgroundColor = Color.White
                ) {
                    Column(
                        Modifier.padding(15.dp)
                    ) {
                        Text(
                            "Welcome to Pathhelper2e!",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontFamily = FontFamily.Serif,
                            fontSize = 90.sp
                        )
                        Button(
                            onClick = {
                                applicationVM.page.value = Pages.CreatureMainStatsPage
                                println(applicationVM.page.value)
                            }
                        ) {
                            Text("Creature Creator")
                        }
                    }
                }
                val items = mutableStateListOf<CreatureCharacteristicCard>(
                    CreatureCharacteristicCard.BasicCharacteristicCard("0"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("1"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("2"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("3"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("4"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("5"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("6"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("7"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("8"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("9"),
                    CreatureCharacteristicCard.BasicCharacteristicCard("10"))
                dragAndDropGrid(items)
            }
        }
    }
}
