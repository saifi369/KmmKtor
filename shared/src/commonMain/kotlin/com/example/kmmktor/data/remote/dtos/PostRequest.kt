package com.example.kmmktor.data.remote.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val userId: Int? = null,
    val title: String? = null,
    @SerialName("body")
    val content: String? = null
)