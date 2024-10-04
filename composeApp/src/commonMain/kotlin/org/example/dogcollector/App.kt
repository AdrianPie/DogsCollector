package org.example.dogcollector

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.example.dogcollector.presentation.screen.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App(
) {
    MaterialTheme {
       HomeScreen()
    }
}

