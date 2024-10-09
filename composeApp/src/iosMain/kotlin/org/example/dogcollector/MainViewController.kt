package org.example.dogcollector

import androidx.compose.ui.window.ComposeUIViewController
import org.example.dogcollector.di.initializeKoin


fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) {
    App()
}