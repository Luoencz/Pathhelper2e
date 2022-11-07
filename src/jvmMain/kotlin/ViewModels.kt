import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

class ApplicationVM {
    val page = mutableStateOf("HomePage")
}

@Composable
fun navigate(applicationVM: ApplicationVM) {
    when (applicationVM.page.value) {
        "HomePage" -> homePage(applicationVM)
        "CreatureCreatorPage" -> creatureCreatorPage(applicationVM)
    }
}
