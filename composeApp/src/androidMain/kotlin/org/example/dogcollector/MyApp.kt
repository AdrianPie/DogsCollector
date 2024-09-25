package org.example.dogcollector

import android.app.Application
import io.ktor.client.engine.okhttp.OkHttp
import org.example.composesharedproject.networking.createHttpClient
import org.example.dogcollector.db.getDogsDataBase


class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provideDogsRepository(getDogsDataBase(this))
        Graph.provideRandomDogClient(createHttpClient(OkHttp.create()))
    }
}