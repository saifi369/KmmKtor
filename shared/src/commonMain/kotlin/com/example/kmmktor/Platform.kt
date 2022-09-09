package com.example.kmmktor

import io.ktor.client.*

expect class Platform() {
    val platform: String
}

expect fun initLogger()

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient