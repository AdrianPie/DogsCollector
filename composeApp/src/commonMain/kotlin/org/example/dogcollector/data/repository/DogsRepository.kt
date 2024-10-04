package org.example.dogcollector.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.dogcollector.data.db.DogsDao
import org.example.dogcollector.data.model.Dog

class DogsRepository(private val dogsDao: DogsDao) {
    fun getAllDogs(): Flow<List<Dog>> = dogsDao.getAllDogs()
    suspend fun upsertDog(dog: Dog) = dogsDao.upsert(dog)
    suspend fun deleteDog(dog: Dog) = dogsDao.delete(dog)
}