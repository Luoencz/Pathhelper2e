package pages

import Pages
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            }
        }
    }
}
