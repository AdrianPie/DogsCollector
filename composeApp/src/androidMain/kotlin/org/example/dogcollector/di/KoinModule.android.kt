package org.example.dogcollector.di

import io.ktor.client.engine.okhttp.OkHttp
import org.example.composesharedproject.networking.createHttpClient
import org.example.dogcollector.chatGpt.ChatGptClient
import org.example.dogcollector.db.getDogsDataBase
import org.example.dogcollector.networking.RandomDogClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val targetModule = module {
    single { getDogsDataBase(context = get()) }
    single { RandomDogClient(createHttpClient(OkHttp.create())) }
    single { ChatGptClient(createHttpClient(OkHttp.create())) }
}