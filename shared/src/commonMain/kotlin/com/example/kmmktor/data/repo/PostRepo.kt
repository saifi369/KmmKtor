package com.example.kmmktor.data.repo

import com.example.kmmktor.data.DataResponse
import com.example.kmmktor.data.remote.dtos.PostRequest
import com.example.kmmktor.data.remote.dtos.PostResponseItem
import kotlinx.coroutines.flow.Flow

interface PostRepo {
    fun getAllPosts(): Flow<DataResponse<List<PostResponseItem>>>
    fun createPost(post: PostRequest): Flow<DataResponse<PostResponseItem>>
    fun getPostById(postId: Int): Flow<DataResponse<PostResponseItem>>
}