package org.example.dogcollector.di


import org.example.dogcollector.data.db.DogsDatabase
import org.example.dogcollector.data.db.getRoomDataBase
import org.example.dogcollector.data.repository.DogsRepository
import org.example.dogcollector.data.usecase.DeleteDogUseCase
import org.example.dogcollector.data.usecase.GetAllDogsUseCase
import org.example.dogcollector.data.usecase.UpsertDogUseCase
import org.example.dogcollector.presentation.screen.home.HomeViewModel

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf

import org.koin.dsl.module

expect val targetModule: Module

val sharedModule = module {
    single { getRoomDataBase(get()) }
    single { get<DogsDatabase>().dogsDao() }
    single { DogsRepository(get()) }

    single { DeleteDogUseCase(get()) }
    single { GetAllDogsUseCase(get()) }
    single { UpsertDogUseCase(get()) }
    viewModelOf(::HomeViewModel)
}

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null
){
    startKoin {
        config?.invoke(this)
        modules(targetModule, sharedModule)
    }
}