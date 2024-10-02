package org.example.dogcollector.di

import org.example.dogcollector.db.getDogsDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val targetModule = module {
    getDogsDatabase()
}