package com.gug.example.posts.network

import com.gug.example.posts.network.dtos.DtoComment
import com.gug.example.posts.network.dtos.DtoPost
import com.gug.example.posts.network.dtos.DtoUser
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsAPI {

    @GET("/posts")
    fun getPosts():
            Deferred<List<DtoPost>>

    @GET("/users")
    fun getUserById(
        @Query("id") userId: String
    ): Deferred<List<DtoUser>>

    @GET("/comments")
    fun getCommentsByPostId(
        @Query("postId") postId: String
    ): Deferred<List<DtoComment>>

}