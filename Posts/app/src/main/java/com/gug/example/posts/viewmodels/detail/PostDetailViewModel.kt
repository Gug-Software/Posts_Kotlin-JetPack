package com.gug.example.posts.viewmodels.detail

import android.app.Application
import androidx.lifecycle.LiveData
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.domain.Comment
import com.gug.example.posts.domain.Post
import com.gug.example.posts.repository.detail.PostDetailRepository
import com.gug.example.posts.ui.detail.IContractDetailPost
import com.gug.example.posts.viewmodels.BaseAndroidViewModel
import kotlinx.coroutines.launch

class PostDetailViewModel(
    postsDataBase: PostsDataBase,
    application: Application,
    val post: Post
) : BaseAndroidViewModel(application), IContractDetailPost.ViewModel {

    val repository = PostDetailRepository(postsDataBase)

    init {
        uiScope.launch {
            refreshUserByPost()
            refreshCommentsByPostId()
        }
    }

    override suspend fun refreshUserByPost() {
        uiScope.launch {
            repository.refreshUserByPost(post)
            //repository.getUserById(post.userId)
        }
    }

    override suspend fun refreshCommentsByPostId() {
        uiScope.launch {
            repository.refreshCommentsByPostId(post.id)
            //repository.getUserById(post.userId)
        }
    }

    override fun setPostFavoriteValue(post: Post) {
        uiScope.launch {
            post.favorite = !post.favorite
            repository.updatePost(post)
        }
    }

    override fun getCommentsByPost(): LiveData<List<Comment>> =
        repository.getCommentsByPostId(postId = post.id)


}