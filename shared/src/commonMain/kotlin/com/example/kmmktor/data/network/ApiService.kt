package com.example.kmmktor.data.network

import com.example.kmmktor.data.DataResponse
import com.example.kmmktor.data.ResponseHandler
import com.example.kmmktor.data.remote.dtos.PostRequest
import com.example.kmmktor.data.remote.dtos.PostResponseItem
import com.example.kmmktor.httpClient
import com.example.kmmktor.initLogger
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

interface ApiService {
    suspend fun getPosts(): Flow<DataResponse<List<PostResponseItem>>>
    suspend fun getPostById(postId: Int): Flow<DataResponse<PostResponseItem>>
    suspend fun createPost(post: PostRequest): Flow<DataResponse<PostResponseItem>>

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(
                client = httpClient {
                    /*
                    //logging
                    install(Logging) {
                        logger = object : Logger {
                            override fun log(message: String) {
                            Napier.v(tag = "KtorTag", message = message)
                        }
                        }
                        level = LogLevel.ALL
                    }*/

                    /*
                    //Config timeout
                    install(HttpTimeout) {
                        requestTimeoutMillis = 30 * 1000L
                        connectTimeoutMillis = 10 * 1000L
                    }
                     */

                    /*
                    //Config Base Url
                    defaultRequest {
                        url {
                            host = ApiRoutes.BASE_URL
                            protocol = URLProtocol.HTTPS
                        }
                        header(HttpHeaders.ContentType, Json)
                    }
                     */

                    install(ContentNegotiation) {
                        json(json = Json {
                            prettyPrint = true
                            isLenient = true
                        })
                    }

                }.also {
                       initLogger()
                },
                responseHandler = ResponseHandler()
            )
        }
    }
}