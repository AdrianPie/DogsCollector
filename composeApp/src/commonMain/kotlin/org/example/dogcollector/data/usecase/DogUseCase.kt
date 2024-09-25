package org.example.dogcollector.data.usecase

data class DogUseCase(
    val deleteDogUseCase: DeleteDogUseCase,
    val getAllDogsUseCase: GetAllDogsUseCase,
    val upsertDogUseCase: UpsertDogUseCase
) {
}