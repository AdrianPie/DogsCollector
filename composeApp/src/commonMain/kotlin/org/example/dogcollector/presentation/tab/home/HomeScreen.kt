package org.example.dogcollector.presentation.tab.home

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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.example.dogcollector.data.model.Dog
import org.example.dogcollector.presentation.screen.screenTwo.TestScreen
import org.koin.compose.viewmodel.koinViewModel


class HomeScreen(): Screen{
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<HomeViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Home Screen")
            Button(
                onClick = { navigator.push(TestScreen(2))}
            ){
                Text("Go to dupa")
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