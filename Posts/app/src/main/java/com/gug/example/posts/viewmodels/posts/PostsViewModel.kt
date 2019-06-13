package com.gug.example.posts.viewmodels.posts

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.domain.Post
import com.gug.example.posts.network.NetworkApiStatus
import com.gug.example.posts.repository.posts.PostsRepository
import com.gug.example.posts.ui.posts.IContractPosts
import com.gug.example.posts.viewmodels.BaseAndroidViewModel
import kotlinx.coroutines.launch

class PostsViewModel(
    postsDataBase: PostsDataBase,
    application: Application,
    showFavorites: Boolean
) : BaseAndroidViewModel(application), IContractPosts.ViewModel {

    val postsRepository = PostsRepository(postsDataBase)
    val posts = getPosts(showFavorites)

    private val _navToDetailPost = MutableLiveData<Post>()
    val navToDetailPost: LiveData<Post>
        get() = _navToDetailPost

    private val _status = MutableLiveData<NetworkApiStatus>()
    val status: LiveData<NetworkApiStatus>
        get() = _status

    init {
        uiScope.launch {
            try {
                _status.value = NetworkApiStatus.LOADING
                refreshPosts()
                _status.value = NetworkApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NetworkApiStatus.ERROR
            }
        }
    }

    override fun setPostFavoriteValue(post: Post) {
        uiScope.launch {
            post.favorite = !post.favorite
            postsRepository.updatePost(post)
        }
    }

    override fun setPostRead(post: Post) {
        uiScope.launch {
            post.read = true
            postsRepository.updatePost(post)
        }
        _navToDetailPost.value = post
    }

    suspend override fun refreshPosts() {
        postsRepository.refreshPosts()
    }

    override fun getPosts(showFavorites: Boolean): LiveData<List<Post>> =
        when (showFavorites) {
            true -> postsRepository.getFavoritesPosts()
            else -> postsRepository.getAllPosts()
        }

    fun onPostDetailNavigated() {
        _navToDetailPost.value = null
    }

}