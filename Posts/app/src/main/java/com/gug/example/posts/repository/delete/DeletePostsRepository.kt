package com.gug.example.posts.repository.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gug.example.posts.database.PostsDataBase
import com.gug.example.posts.ui.delete.IContractDeletePosts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeletePostsRepository(
    val database: PostsDataBase
) : IContractDeletePosts.Model {

    override suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            database.commentDao.deleAllComments()
            database.userDao.deleteAllUsers()
            database.postDao.deleteAll()
        }
    }

    val posts = getAllPosts()
    override fun getAllPosts() = database.postDao.getPostsAllLive()

}