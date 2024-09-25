package org.example.dogcollector.data.usecase

import org.example.dogcollector.data.repository.DogsRepository

class GetAllDogsUseCase(private val dogsRepository: DogsRepository) {
    operator fun invoke() = dogsRepository.getAllDogs()
}
