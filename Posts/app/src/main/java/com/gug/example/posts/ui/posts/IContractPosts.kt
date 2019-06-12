package com.gug.example.posts.ui.posts

import androidx.lifecycle.LiveData
import com.gug.example.posts.domain.Post

interface IContractPosts {

    interface View {}

    interface ViewModel {
        fun setPostFavoriteValue(post: Post)
        fun setPostRead(post: Post)
        fun getPosts(showFavorites: Boolean): LiveData<List<Post>>
        suspend fun refreshPosts()
    }

    interface Model {
        fun getAllPosts(): LiveData<List<Post>>
        fun getFavoritesPosts(): LiveData<List<Post>>
        suspend fun updatePost(post: Post)
        suspend fun refreshPosts()
    }
}