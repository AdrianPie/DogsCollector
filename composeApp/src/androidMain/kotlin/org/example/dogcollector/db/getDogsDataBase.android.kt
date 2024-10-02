package org.example.dogcollector.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.example.dogcollector.data.db.DogsDatabase


fun getDogsDataBase(context: Context): RoomDatabase.Builder<DogsDatabase> {
    val dbFile = context.getDatabasePath("dogs.db")
    return Room.databaseBuilder<DogsDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )

}