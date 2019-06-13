package com.gug.example.posts.repository.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.database.entities.asDomainModel
import com.gug.example.posts.domain.Post
import com.gug.example.posts.domain.asDataBaseModel
import com.gug.example.posts.network.PostsRetrofit
import com.gug.example.posts.network.dtos.asDatabaseModel
import com.gug.example.posts.ui.posts.IContractPosts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsRepository(
    val database: PostsDataBase
) : IContractPosts.Model {

    override fun getAllPosts(): LiveData<List<Post>> =
        Transformations.map(database.postDao.getPostsAllLive()) {
            it.asDomainModel()
        }

    override fun getFavoritesPosts(): LiveData<List<Post>> =
        Transformations.map(database.postDao.getPostsFavoritesLive()) {
            it.asDomainModel()
        }

    override suspend fun updatePost(post: Post) {
        withContext(Dispatchers.IO) {
            database.postDao.update(post.asDataBaseModel())
        }
    }

    override suspend fun refreshPosts() {
        withContext(Dispatchers.IO) {
            val postsDb = database.postDao.getPosts()
            if (postsDb.isEmpty()) {
                val playlist = PostsRetrofit.postsApi.getPosts().await()
                database.postDao.insertAll(*playlist.asDatabaseModel())
            }
        }
    }

}