package com.gug.example.posts.ui.detail

import androidx.lifecycle.LiveData
import com.gug.example.posts.database.entities.DbUser
import com.gug.example.posts.domain.Comment
import com.gug.example.posts.domain.Post
import com.gug.example.posts.domain.User

interface IContractDetailPost {

    interface View {}

    interface ViewModel {
        suspend fun refreshUserByPost()
        suspend fun refreshCommentsByPostId()
        fun setPostFavoriteValue(post: Post)
        fun getCommentsByPost(): LiveData<List<Comment>>
    }

    interface Model {
        suspend fun refreshUserByPost(post: Post)
        suspend fun refreshCommentsByPostId(postId: Int)
        suspend fun updatePost(post: Post)
        suspend fun getUserById(userId: Int): User?
        fun getPostById(postId: Int): LiveData<Post>
        fun getCommentsByPostId(postId: Int): LiveData<List<Comment>>
    }
}