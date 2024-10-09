package org.example.dogcollector.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.composesharedproject.util.onError
import org.example.composesharedproject.util.onSuccess
import org.example.dogcollector.data.db.DogsDatabase
import org.example.dogcollector.data.model.Dog
import org.example.dogcollector.data.usecase.DeleteDogUseCase
import org.example.dogcollector.data.usecase.GetAllDogsUseCase
import org.example.dogcollector.data.usecase.UpsertDogUseCase
import org.example.dogcollector.networking.RandomDogClient

class HomeViewModel(
    private val deleteDogUseCase: DeleteDogUseCase,
    private val getAllDogsUseCase: GetAllDogsUseCase,
    private val upsertDogUseCase: UpsertDogUseCase,
    private val dataBase: DogsDatabase,
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

