package org.example.dogcollector.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Dog(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val photoURL: String = ""
)
