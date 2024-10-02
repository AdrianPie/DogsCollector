package org.example.dogcollector.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.dogcollector.data.model.Dog

@Database(
    entities = [Dog::class],
    version = 1,
    exportSchema = true
)

@ConstructedBy(DogsDatabaseConstructor::class)
abstract class DogsDatabase: RoomDatabase() {
    abstract fun dogsDao(): DogsDao

}

@Suppress("NO_ACTUAL_FOR_EXPECT",)
expect object DogsDatabaseConstructor: RoomDatabaseConstructor<DogsDatabase> {
    override fun initialize(): DogsDatabase
}
