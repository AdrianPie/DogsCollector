package org.example.composesharedproject.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.example.composesharedproject.util.NetworkError
import org.example.composesharedproject.util.Result

class RandomDogClient(
    private val httpClient: HttpClient
) {

    suspend fun getRandomDogPhoto(): Result<String, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://dog.ceo/api/breeds/image/random"
            )
        } catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val randomDog = response.body<RandomDog>()
                Result.Success(randomDog.message)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}