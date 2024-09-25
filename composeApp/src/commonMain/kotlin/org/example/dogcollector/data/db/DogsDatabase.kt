package org.example.dogcollector.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.dogcollector.data.model.Dog

@Database(
    entities = [Dog::class],
    version = 1
)
abstract class DogsDatabase: RoomDatabase() {
    abstract fun dogsDao(): DogsDao

}

fun getRoomDataBase(
    builder: RoomDatabase.Builder<DogsDatabase>
): DogsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}