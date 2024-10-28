package org.example.dogcollector.di

import io.ktor.client.engine.darwin.Darwin
import org.example.composesharedproject.networking.createHttpClient
import org.example.dogcollector.chatGpt.ChatGptClient
import org.example.dogcollector.db.getDogsDatabase
import org.example.dogcollector.networking.RandomDogClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val targetModule = module {
    getDogsDatabase()

    single { RandomDogClient(createHttpClient(Darwin.create())) }
    single { ChatGptClient(createHttpClient(Darwin.create())) }
}