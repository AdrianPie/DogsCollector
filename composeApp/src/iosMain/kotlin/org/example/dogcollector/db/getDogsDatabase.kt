package org.example.dogcollector.db

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.dogcollector.data.db.DogsDatabase
import platform.Foundation.NSHomeDirectory

fun getDogsDatabase(): RoomDatabase.Builder<DogsDatabase> {
    val dbFile = NSHomeDirectory() + "/dogs.db"
    return Room.databaseBuilder<DogsDatabase>(
        name = dbFile,
        factory = { DogsDatabase::class.instantiateImpl()}
    )

}