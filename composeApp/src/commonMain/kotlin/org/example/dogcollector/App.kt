package org.example.dogcollector

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

import kotlinx.coroutines.launch
import org.example.composesharedproject.networking.RandomDogClient
import org.example.dogcollector.data.model.Dog
import org.example.dogcollector.data.db.DogsDao
import org.example.dogcollector.presentation.MainViewModel
import org.example.dogcollector.presentation.MainViewModelFactory
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App(
) {
    MaterialTheme {

       //MainScreen()
    }
}

@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val dogs by viewModel.dogList.collectAsState()
    val scope = rememberCoroutineScope()

    val randomDog by viewModel.randomDog.collectAsState()
    val randomDogBreed by viewModel.randomDogBreed.collectAsState()

    LaunchedEffect(true){
        val dogsList = listOf(
            Dog(photoURL = "https://images.dog.ceo/breeds/terrier-irish/n02093991_1003.jpg"),

        )
        dogsList.forEach {
            viewModel.insertDog(it)
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment =  Alignment.CenterHorizontally,
        ) {
        Button(onClick = {
            viewModel.getRandomDog()
        }
        ) {
            Text("Add Dog")
        }
        Box(
            modifier = Modifier
                .size(250.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray)
        ){
            coil3.compose.AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = randomDog,
                contentDescription = "test",
                contentScale = ContentScale.Crop,
                )
        }
        Text(randomDogBreed)

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(dogs){ dog ->
                Text(
                    text = dog.photoURL,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                viewModel.deleteDog(dog)
                            }
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}