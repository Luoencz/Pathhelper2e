import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun App() {
    val applicationVM = remember { ApplicationVM() }
    navigate(applicationVM)
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pathhelper2e"
    ) {
        App()
    }
}
