import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import models.*

@Composable
fun App() {
    val applicationVM = remember { ApplicationVM() }
    navigate(applicationVM)
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(WindowPlacement.Maximized),
        title = "Pathhelper2e"
    ) {
        App()
    }
}
