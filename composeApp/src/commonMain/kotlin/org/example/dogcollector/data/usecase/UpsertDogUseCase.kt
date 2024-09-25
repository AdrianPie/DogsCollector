package org.example.dogcollector.data.usecase

import org.example.dogcollector.data.repository.DogsRepository
import org.example.dogcollector.data.model.Dog

class UpsertDogUseCase(private val dogsRepository: DogsRepository) {
    suspend operator fun invoke(dog: Dog) = dogsRepository.upsertDog(dog)
}