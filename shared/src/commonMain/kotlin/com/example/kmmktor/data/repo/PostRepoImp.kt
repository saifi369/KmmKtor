package com.example.kmmktor.data.repo

import com.example.kmmktor.data.DataResponse
import com.example.kmmktor.data.network.ApiService
import com.example.kmmktor.data.remote.dtos.PostRequest
import com.example.kmmktor.data.remote.dtos.PostResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class PostRepoImp(
    private val service: ApiService
) : PostRepo {

    override fun getAllPosts(): Flow<DataResponse<List<PostResponseItem>>> = channelFlow {
        service.getPosts().collect {
            send(it)
        }
    }

    override fun createPost(post: PostRequest): Flow<DataResponse<PostResponseItem>> = channelFlow {
        service.createPost(post = post).collect{
            send(it)
        }
    }

    override fun getPostById(postId: Int): Flow<DataResponse<PostResponseItem>> = channelFlow {
        service.getPostById(postId).collect{
            send(it)
        }
    }
}