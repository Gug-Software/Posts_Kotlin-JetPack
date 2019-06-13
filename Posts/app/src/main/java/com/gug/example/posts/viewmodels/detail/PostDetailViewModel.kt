package com.gug.example.posts.viewmodels.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.domain.Comment
import com.gug.example.posts.domain.Post
import com.gug.example.posts.domain.User
import com.gug.example.posts.network.NetworkApiStatus
import com.gug.example.posts.repository.detail.PostDetailRepository
import com.gug.example.posts.ui.detail.IContractDetailPost
import com.gug.example.posts.viewmodels.BaseAndroidViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PostDetailViewModel(
    postsDataBase: PostsDataBase,
    application: Application,
    val post: Post
) : BaseAndroidViewModel(application), IContractDetailPost.ViewModel {

    val repository = PostDetailRepository(postsDataBase)

    private val _status = MutableLiveData<NetworkApiStatus>()
    val status: LiveData<NetworkApiStatus>
        get() = _status

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    val comments = getCommentsByPost()
    val currentPost = repository.getPostById(post.id)

    init {
        uiScope.launch {
            refreshUserByPost()
            refreshCommentsByPostId()
        }
    }

    override suspend fun refreshUserByPost() {
        uiScope.launch {
            try {
                _status.value = NetworkApiStatus.LOADING
                repository.refreshUserByPost(post)
                _user.value = repository.getUserById(post.userId)
                _status.value = NetworkApiStatus.DONE
            } catch (e: HttpException) {
                _status.value = NetworkApiStatus.ERROR
            } catch (e: Throwable) {
                _status.value = NetworkApiStatus.ERROR
            }
        }
    }

    override suspend fun refreshCommentsByPostId() {
        uiScope.launch {
            try {
                _status.value = NetworkApiStatus.LOADING
                repository.refreshCommentsByPostId(post.id)
                _status.value = NetworkApiStatus.DONE
            } catch (e: HttpException) {
                _status.value = NetworkApiStatus.ERROR
            } catch (e: Throwable) {
                _status.value = NetworkApiStatus.ERROR
            }
        }
    }

    override fun setPostFavoriteValue() {
        uiScope.launch {
            currentPost.value?.favorite = !(currentPost.value?.favorite)!!
            repository.updatePost(currentPost.value!!)
        }
    }

    override fun getCommentsByPost(): LiveData<List<Comment>> =
        repository.getCommentsByPostId(postId = post.id)


}