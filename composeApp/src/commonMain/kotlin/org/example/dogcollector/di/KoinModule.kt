package org.example.dogcollector.di

import org.example.dogcollector.data.db.getRoomDataBase
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect val targetModule: Module

val sharedModule = module {
    single { getRoomDataBase(get()) }
}

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null
){
    startKoin {
        config?.invoke(this)
        modules(targetModule, sharedModule)
    }

}