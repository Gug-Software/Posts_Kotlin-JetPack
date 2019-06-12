package com.gug.example.posts.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gug.example.posts.database.entities.DbPost

@Dao
interface PostDao {

    @Query("select * from DbPost")
    fun getPostsAllLive(): LiveData<List<DbPost>>

    @Query("select * from DbPost where id=:postId")
    fun getPostsByIdLive(postId: Int): LiveData<DbPost>

    @Query("select * from DbPost where favorite=1")
    fun getPostsFavoritesLive(): LiveData<List<DbPost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DbPost)

    @Query("DELETE FROM dbpost")
    fun deleteAll()

    @Update
    fun update(post: DbPost)

    @Query("select * from DbPost")
    fun getPosts(): List<DbPost>

}