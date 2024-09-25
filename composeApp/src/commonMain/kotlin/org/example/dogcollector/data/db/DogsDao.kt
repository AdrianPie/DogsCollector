package org.example.dogcollector.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.example.dogcollector.data.model.Dog

@Dao
interface DogsDao {
    @Query("Select * from Dog")
     fun getAllDogs(): Flow<List<Dog>>

    @Upsert
    suspend fun upsert(dog: Dog)

    @Delete
    suspend fun delete(dog: Dog)


}