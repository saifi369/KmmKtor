package com.example.kmmktor.data.network

import com.example.kmmktor.data.DataResponse
import com.example.kmmktor.data.ResponseHandler
import com.example.kmmktor.data.remote.dtos.PostRequest
import com.example.kmmktor.data.remote.dtos.PostResponseItem
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiServiceImpl(
    private val client: HttpClient,
    private val responseHandler: ResponseHandler
) : ApiService {

    override suspend fun getPosts(): Flow<DataResponse<List<PostResponseItem>>> = flow {
        try {
            val response = client.get { url(ApiRoutes.POSTS_URL) }
            emit(responseHandler.handleSuccess(response.body()))
        } catch (e: Exception) {
            emit(responseHandler.handleException(e))
        }
    }

    override suspend fun getPostById(postId: Int): Flow<DataResponse<PostResponseItem>> =
        flow {
            try {
                val response = client.get {
                    url {
                        //path parameters
                        appendPathSegments(ApiRoutes.POSTS_URL, postId.toString())
                        //query parameters
                        appendPathSegments(ApiRoutes.POSTS_URL)
                        parameter("id", postId.toString())
                    }
                }
//                Napier.v(tag = "KtorKmm", message = response.bodyAsText())
                emit(responseHandler.handleSuccess(response.body()))
            }
//            } catch (e: RedirectResponseException) {
//                // 3xx - responses
//                Napier.v(tag = "KtorKmm", message = "Error: ${e.response.status.description}")
//                emit(responseHandler.handleException(e))
//            } catch (e: ClientRequestException) {
//                // 4xx - responses
//                Napier.v(tag = "KtorKmm", message = "Error: ${e.response.status.description}")
//                emit(responseHandler.handleException(e))
//            } catch (e: ServerResponseException) {
//                //5xx - responses
//                Napier.v(tag = "KtorKmm", message = "Error: ${e.response.status.description}")
//                emit(responseHandler.handleException(e))
//            }
            catch (e: Exception) {
//                Napier.v(tag = "KtorKmm", message = e.message!!)
                emit(responseHandler.handleException(e))
            }
        }

    override suspend fun createPost(post: PostRequest): Flow<DataResponse<PostResponseItem>> =
        flow {
            try {
                val response = client.post {
                    url(ApiRoutes.POSTS_URL)
                    contentType(ContentType.Application.Json)
                    setBody(post)
                }
                emit(responseHandler.handleSuccess(response.body()))
            } catch (e: Exception) {
                emit(responseHandler.handleException(e))
            }
        }
}