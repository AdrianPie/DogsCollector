package org.example.dogcollector

import android.app.Application
import io.ktor.client.engine.okhttp.OkHttp
import org.example.composesharedproject.networking.createHttpClient
import org.example.dogcollector.db.getDogsDataBase
import org.example.dogcollector.di.initializeKoin
import org.koin.android.ext.koin.androidContext


class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@MyApp)
        }

    }
}