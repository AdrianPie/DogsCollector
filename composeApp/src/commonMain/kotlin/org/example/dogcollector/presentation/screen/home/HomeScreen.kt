package org.example.dogcollector.presentation.screen.home

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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.dogcollector.data.model.Dog
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()
    val dogs by viewModel.dogList.collectAsState()
    val scope = rememberCoroutineScope()

    val randomDog by viewModel.randomDog.collectAsState()
    val randomDogBreed by viewModel.randomDogBreed.collectAsState()
    val testTextChat by viewModel.test.collectAsState()

    LaunchedEffect(true){
        val dogsList = listOf(
            Dog(photoURL = "https://images.dog.ceo/breeds/terrier-irish/n02093991_1003.jpg"),

            )
        dogsList.forEach {
            viewModel.insertDog(it)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Dog Collector") })
        }
    ) {

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
        ChatInputField(
            onSendMessage = { message ->
               viewModel.getChatResponse(message)
            },
            onSendMessage2 = { message ->
                viewModel.getChatResponseTwo(message)
            }
        )
        Text(text = testTextChat)
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
@Composable
fun ChatInputField(
    label: String = "Enter your message...",
    onSendMessage: (String) -> Unit,
    onSendMessage2: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column {
        // TextField where the user can type
        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            maxLines = 1
        )

        // Button to send the message
        Button(
            onClick = {
                onSendMessage(text) // Invoke the callback with the current text
                text = "" // Clear the input field after sending
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = text.isNotEmpty() // Disable the button if the text field is empty
        ) {
            Text("Send")
        }
        Button(
            onClick = {
                onSendMessage2(text) // Invoke the callback with the current text
                text = "" // Clear the input field after sending
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = text.isNotEmpty() // Disable the button if the text field is empty
        ) {
            Text("Send2")
        }
    }
}