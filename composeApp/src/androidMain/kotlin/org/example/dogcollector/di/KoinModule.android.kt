package org.example.dogcollector.di


import org.example.dogcollector.db.getDogsDataBase

import org.koin.dsl.module

actual val targetModule = module {
    single { getDogsDataBase(context = get()) }

}