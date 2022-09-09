package com.example.kmmktor.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.kmmktor.Greeting
import com.example.kmmktor.data.network.ApiService

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val client = ApiService.create()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
//            val posts = produceState<List<PostResponseItem>>(
//                initialValue = emptyList(),
//                producer = {
//                    value = client.getPosts()
//                }
//            )
            val flowPosts by viewModel.list.collectAsState()
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(all = 16.dp)
                        ) {
                            items(flowPosts) {
                                if (flowPosts.isEmpty())
                                    Text(text = "Loading posts...", fontSize = 22.sp)
                                else {
                                    Text(
                                        text = "${it.title}",
                                        fontSize = 18.sp,
                                        modifier = Modifier.background(
                                            Color.Gray
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(
                                all = 16.dp
                            )
                        ) {
                            Button(onClick = { viewModel.fetchPosts() }) {
                                Text(text = "Request API")
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Button(onClick = { viewModel.clearData() }) {
                                Text(text = "Clear Data")
                            }

                        }
                    }
                }
            }
        }
    }
}