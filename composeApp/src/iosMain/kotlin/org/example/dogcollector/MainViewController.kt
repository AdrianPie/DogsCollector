package org.example.dogcollector

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin
import org.example.composesharedproject.networking.RandomDogClient
import org.example.composesharedproject.networking.createHttpClient
import org.example.dogcollector.db.getDogsDatabase
import org.example.dogcollector.di.initializeKoin


fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) {
    App()
}