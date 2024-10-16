package org.example.dogcollector.chatGpt

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.example.composesharedproject.networking.RandomDog
import org.example.composesharedproject.util.NetworkError
import org.example.composesharedproject.util.Result
const val apiKey = "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"
class ChatGptClient(
    private val httpClient: HttpClient
) {

    suspend fun sendChatMessage(messages: List<Message>): ChatResponse {
        return httpClient.post("https://api.openai.com/v1/chat/completions") {
            header(HttpHeaders.Authorization, "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(ChatRequest(
                model = "gpt-3.5-turbo",
                messages = messages
            ))
        }.body()
    }

}
