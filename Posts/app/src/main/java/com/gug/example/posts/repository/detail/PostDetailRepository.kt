package com.gug.example.posts.repository.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.database.entities.DbUser
import com.gug.example.posts.database.entities.asDomainModel
import com.gug.example.posts.domain.Comment
import com.gug.example.posts.domain.Post
import com.gug.example.posts.domain.User
import com.gug.example.posts.domain.asDataBaseModel
import com.gug.example.posts.network.PostsRetrofit
import com.gug.example.posts.network.dtos.asDatabaseModel
import com.gug.example.posts.ui.detail.IContractDetailPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostDetailRepository(
    val database: PostsDataBase
) : IContractDetailPost.Model {

    override suspend fun refreshUserByPost(post: Post) {
        withContext(Dispatchers.IO) {
            val userDB = database.userDao.getUserByIdData(post.userId.toString())
            if (userDB == null) {
                val userPost = PostsRetrofit.postsApi.getUserById(post.userId.toString()).await()
                database.userDao.insertUser(userPost[0].asDatabaseModel())
            }
        }
    }

    override suspend fun refreshCommentsByPostId(postId: Int) {
        withContext(Dispatchers.IO) {
            val commentsPost =
                PostsRetrofit.postsApi.getCommentsByPostId(postId = postId.toString()).await()
            database.commentDao.insertAll(*commentsPost.asDatabaseModel())
        }
    }

    override suspend fun updatePost(post: Post) {
        withContext(Dispatchers.IO) {
            database.postDao.update(post.asDataBaseModel())
        }
    }

    override suspend fun getUserById(userId: Int): User? {
        var dbUser: DbUser? = null
        withContext(Dispatchers.IO) {
            dbUser = database.userDao.getUserByIdData(userID = userId.toString())
        }
        return dbUser?.asDomainModel()
    }

    override fun getPostById(postId: Int): LiveData<Post> =
        Transformations.map(database.postDao.getPostsByIdLive(postId = postId)) {
            it.asDomainModel()
        }

    override fun getCommentsByPostId(postId: Int): LiveData<List<Comment>> =
        Transformations.map(database.commentDao.getCommentsByPostId(postId = postId)) {
            it.asDomainModel()
        }

}