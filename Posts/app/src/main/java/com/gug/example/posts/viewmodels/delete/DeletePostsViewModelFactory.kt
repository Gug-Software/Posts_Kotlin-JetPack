package com.gug.example.posts.viewmodels.delete

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gug.example.posts.database.PostsDataBase

class DeletePostsViewModelFactory(
    private val database: PostsDataBase,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeletePostsViewModel::class.java)) {
            return DeletePostsViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}