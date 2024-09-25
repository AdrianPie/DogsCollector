package org.example.composesharedproject.networking

import kotlinx.serialization.Serializable

@Serializable
data class RandomDog(
    val message: String,
    val status: String,
)
