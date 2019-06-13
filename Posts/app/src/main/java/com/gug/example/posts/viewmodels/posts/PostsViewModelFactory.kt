package com.gug.example.posts.viewmodels.posts

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gug.example.posts.database.PostsDataBase

class PostsViewModelFactory(
    private val database: PostsDataBase,
    private val application: Application,
    private val showFavorites: Boolean
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            return PostsViewModel(database, application, showFavorites) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}