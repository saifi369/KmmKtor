package com.example.kmmktor.android

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmktor.data.DataResponse
import com.example.kmmktor.data.network.ApiService
import com.example.kmmktor.data.remote.dtos.PostRequest
import com.example.kmmktor.data.remote.dtos.PostResponseItem
import com.example.kmmktor.data.repo.PostRepoImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val list = MutableStateFlow<List<PostResponseItem>>(emptyList())
    private val repo = PostRepoImp(ApiService.create())

    init {
//        createPost()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            repo.getAllPosts().collect {
                when (it) {
                    is DataResponse.Error -> Log.d("MyTag", "fetchPosts: Error getting posts")
                    is DataResponse.Loading -> Log.d("MyTag", "fetchPosts: Error getting posts")
                    is DataResponse.Success -> list.emit(it.data!!)
                }
            }
        }
    }

    fun fetchPostById(postId: Int) {
        viewModelScope.launch {
            repo.getPostById(postId).collect {
                when (it) {
                    is DataResponse.Error -> Log.d("MyTag", "fetchPosts: Error getting posts")
                    is DataResponse.Loading -> Log.d("MyTag", "fetchPosts: Error getting posts")
                    is DataResponse.Success -> list.emit(listOf(it.data!!))
                }
            }
        }
    }

    fun createPost(
        post: PostRequest = PostRequest(
            userId = 1,
            title = "New post",
            content = "post body content"
        )
    ) {
        viewModelScope.launch {
            repo.createPost(post).collect {
                when (it) {
                    is DataResponse.Error -> Log.d("MyTag", "fetchPosts: Error getting posts")
                    is DataResponse.Loading -> Log.d("MyTag", "fetchPosts: Error getting posts")
                    is DataResponse.Success -> list.emit(listOf(it.data!!))
                }
            }
        }
    }

    fun clearData() {
        viewModelScope.launch {
            list.emit(emptyList())
        }
    }
}