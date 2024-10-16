package org.example.dogcollector.chatGpt


import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
)

@Serializable
data class Message(
    val role: String,
    val content: String
)

@Serializable
data class ChatResponse(
    val choices: List<Choice>
)

@Serializable
data class Choice(
    val message: Message
)


