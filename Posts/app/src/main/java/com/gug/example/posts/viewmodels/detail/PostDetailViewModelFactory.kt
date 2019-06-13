package com.gug.example.posts.viewmodels.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.domain.Post

class PostDetailViewModelFactory(
    private val database: PostsDataBase,
    private val application: Application,
    private val post: Post
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostDetailViewModel::class.java)) {
            return PostDetailViewModel(database, application, post) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}