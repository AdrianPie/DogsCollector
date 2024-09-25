package org.example.dogcollector.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.dogcollector.data.db.DogsDao
import org.example.dogcollector.data.model.Dog

class DogsRepositoryImpl(private val dogsDao: DogsDao): DogsRepository {
    override fun getAllDogs(): Flow<List<Dog>> = dogsDao.getAllDogs()
    override suspend fun upsertDog(dog: Dog) = dogsDao.upsert(dog)
    override suspend fun deleteDog(dog: Dog) = dogsDao.delete(dog)
}