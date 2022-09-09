package com.example.kmmktor.data.remote.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponseItem(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    @SerialName("body")
    val content: String? = null
)