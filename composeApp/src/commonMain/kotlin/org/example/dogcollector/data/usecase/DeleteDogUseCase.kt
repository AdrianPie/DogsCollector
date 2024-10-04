package org.example.dogcollector.data.usecase

import org.example.dogcollector.data.model.Dog
import org.example.dogcollector.data.repository.DogsRepository

class DeleteDogUseCase(private val dogsRepository: DogsRepository) {
    suspend operator fun invoke(dog: Dog) = dogsRepository.deleteDog(dog)
}