package org.example.dogcollector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.engine.okhttp.OkHttp
import org.example.composesharedproject.networking.RandomDogClient
import org.example.composesharedproject.networking.createHttpClient
import org.example.dogcollector.db.getDogsDataBase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            App(
            )
        }
    }
}
