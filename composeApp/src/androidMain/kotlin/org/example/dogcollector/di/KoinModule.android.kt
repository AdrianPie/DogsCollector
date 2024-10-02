package org.example.dogcollector.di

import org.example.dogcollector.db.getDogsDataBase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val targetModule = module {
    single { getDogsDataBase(context = get()) }
}