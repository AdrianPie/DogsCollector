package org.example.dogcollector.presentation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.composesharedproject.networking.RandomDogClient
import org.example.composesharedproject.util.onError
import org.example.composesharedproject.util.onSuccess
import org.example.dogcollector.data.model.Dog
import org.example.dogcollector.data.usecase.DeleteDogUseCase
import org.example.dogcollector.data.usecase.DogUseCase
import org.example.dogcollector.data.usecase.GetAllDogsUseCase
import org.example.dogcollector.data.usecase.UpsertDogUseCase
import kotlin.reflect.KClass

class MainViewModel(
    private val deleteDogUseCase: DeleteDogUseCase,
    private val getAllDogsUseCase: GetAllDogsUseCase,
    private val upsertDogUseCase: UpsertDogUseCase,
    private val client: RandomDogClient
): ViewModel() {

    private var _dogList = MutableStateFlow<List<Dog>>(emptyList())
    val dogList = _dogList.asStateFlow()

    private var _randomDog = MutableStateFlow(String())
    val randomDog = _randomDog.asStateFlow()

    private var _randomDogBreed = MutableStateFlow(String())
    val randomDogBreed = _randomDogBreed.asStateFlow()



    init {
        getAllDogs()
    }

    fun deleteDog(dog: Dog) {
        viewModelScope.launch {
            deleteDogUseCase.invoke(dog)
        }
    }
    fun insertDog(dog: Dog){
        viewModelScope.launch {
            upsertDogUseCase.invoke(dog)
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

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val dogUseCase: DogUseCase,
    private val client: RandomDogClient
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return MainViewModel(
            dogUseCase.deleteDogUseCase,
            dogUseCase.getAllDogsUseCase,
            dogUseCase.upsertDogUseCase,
            client
        ) as T
    }
}