package org.example.dogcollector.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.composesharedproject.util.onError
import org.example.composesharedproject.util.onSuccess
import org.example.dogcollector.chatGpt.ChatGptClient
import org.example.dogcollector.chatGpt.Message
import org.example.dogcollector.data.db.DogsDatabase
import org.example.dogcollector.data.model.Dog
import org.example.dogcollector.data.usecase.DeleteDogUseCase
import org.example.dogcollector.data.usecase.GetAllDogsUseCase
import org.example.dogcollector.data.usecase.UpsertDogUseCase
import org.example.dogcollector.networking.RandomDogClient
import kotlin.time.Duration.Companion.seconds

class HomeViewModel(
    private val deleteDogUseCase: DeleteDogUseCase,
    private val getAllDogsUseCase: GetAllDogsUseCase,
    private val upsertDogUseCase: UpsertDogUseCase,
    private val dataBase: DogsDatabase,
    private val client: RandomDogClient,
    private val chatClient: ChatGptClient
): ViewModel() {

    private var _dogList = MutableStateFlow<List<Dog>>(emptyList())
    val dogList = _dogList.asStateFlow()

    private var _randomDog = MutableStateFlow(String())
    val randomDog = _randomDog.asStateFlow()

    private var _randomDogBreed = MutableStateFlow(String())
    val randomDogBreed = _randomDogBreed.asStateFlow()

    private var _test = MutableStateFlow("gowno")
    val test = _test.asStateFlow()



    init {
        getAllDogs()
    }

    fun getChatResponseTwo(message: String){
        viewModelScope.launch {
            val response = chatClient.sendChatMessage(
                listOf(
                    Message(
                        role = "user",
                        content = message
                    )
                )
            )
            response.onSuccess {
                _test.value = it.choices.first().message.content ?: "No response"
            }.onError {
                _test.value = it.toString()
            }
        }
    }
    fun getChatResponse(message: String){
        val openAI = OpenAI(
           token =  "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",
           timeout = Timeout(socket = 60.seconds )
        )
        viewModelScope.launch {
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.User,
                        content = message
                    )
                )
            )
            val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
            _test.value = completion.choices.first().message.content ?: "No response"

        }
    }

    fun deleteDog(dog: Dog) {
        viewModelScope.launch {
            deleteDogUseCase.invoke(dog)
//            dataBase.dogsDao().delete(dog)
        }
    }
    fun insertDog(dog: Dog){
        viewModelScope.launch {
            upsertDogUseCase.invoke(dog)
//            dataBase.dogsDao().upsert(dog)
        }

    }

    fun getRandomDog(){
        viewModelScope.launch {
            client.getRandomDogPhoto().onSuccess {
                _randomDog.value = it
                extractDogBreed(it)
            }.onError {
                _randomDog.value = it.name
            }
        }
    }
    private fun getAllDogs() {
        viewModelScope.launch {
            dataBase.dogsDao().getAllDogs().collect{
                _dogList.value = it
            }
             getAllDogsUseCase.invoke().collect{
                 _dogList.value = it
             }
        }
    }

    private fun extractDogBreed(url: String) {

        val parts = url.split("/")

        val breedPart = parts.getOrNull(parts.indexOf("breeds") + 1)

        val breed = breedPart?.split("-")?.joinToString(" ") {
            it.replaceFirstChar { char -> char.uppercase() }
        }

        _randomDogBreed.value = breed ?: "Unknown"
    }
}

