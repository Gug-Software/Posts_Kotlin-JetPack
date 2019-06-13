package com.gug.example.posts.viewmodels.delete

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.database.entities.asDomainModel
import com.gug.example.posts.repository.delete.DeletePostsRepository
import com.gug.example.posts.ui.delete.IContractDeletePosts
import com.gug.example.posts.viewmodels.BaseAndroidViewModel
import kotlinx.coroutines.launch

class DeletePostsViewModel(
    postsDataBase: PostsDataBase,
    application: Application
) : BaseAndroidViewModel(application), IContractDeletePosts.ViewModel {

    val repository = DeletePostsRepository(postsDataBase)

    val allPostsSize = Transformations.map(repository.posts) {
        it.size.toString()
    }

    val favPostsSize = Transformations.map(repository.posts) {
        it.filter { it.favorite }.size.toString()
    }

    val readedPostsSize = Transformations.map(repository.posts) {
        it.filter { it.read }.size.toString()
    }

    val postsSize = Transformations.map(repository.posts) {
        it.size
    }

    override fun deleteAll() {
        uiScope.launch {
            repository.deleteAll()
        }

    }


}