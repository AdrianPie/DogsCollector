package org.example.dogcollector

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform