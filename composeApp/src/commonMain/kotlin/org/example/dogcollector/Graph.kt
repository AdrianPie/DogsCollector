package org.example.dogcollector

import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.example.composesharedproject.networking.RandomDogClient
import org.example.dogcollector.data.repository.DogsRepository
import org.example.dogcollector.data.repository.DogsRepositoryImpl
import org.example.dogcollector.data.db.DogsDatabase
import org.example.dogcollector.data.db.getRoomDataBase
import org.example.dogcollector.data.usecase.DeleteDogUseCase
import org.example.dogcollector.data.usecase.DogUseCase
import org.example.dogcollector.data.usecase.GetAllDogsUseCase
import org.example.dogcollector.data.usecase.UpsertDogUseCase

object Graph {
    lateinit var dogsRepository: DogsRepository
    lateinit var dogsUseCase: DogUseCase

    lateinit var deleteDogUseCase: DeleteDogUseCase
    lateinit var getAllDogsUseCase: GetAllDogsUseCase
    lateinit var upsertDogUseCase: UpsertDogUseCase

    lateinit var randomDogClient: RandomDogClient

    fun provideDogsRepository(dataBaseBuilder:  RoomDatabase.Builder<DogsDatabase>) {
        val database = getRoomDataBase(dataBaseBuilder)
        dogsRepository = DogsRepositoryImpl(database.dogsDao())

        deleteDogUseCase = DeleteDogUseCase(dogsRepository)
        getAllDogsUseCase = GetAllDogsUseCase(dogsRepository)
        upsertDogUseCase = UpsertDogUseCase(dogsRepository)
        dogsUseCase = DogUseCase(deleteDogUseCase, getAllDogsUseCase, upsertDogUseCase)
    }
    fun provideRandomDogClient(engine: HttpClient) {
        randomDogClient = RandomDogClient(engine)

    }
}