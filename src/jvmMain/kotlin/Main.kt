// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun App() {
    val applicationVM =  remember { ApplicationVM() }
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
