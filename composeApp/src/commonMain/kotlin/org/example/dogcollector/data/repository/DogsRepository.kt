package org.example.dogcollector.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.dogcollector.data.model.Dog

interface DogsRepository {
    fun getAllDogs(): Flow<List<Dog>>
    suspend fun upsertDog(dog: Dog)
    suspend fun deleteDog(dog: Dog)
}