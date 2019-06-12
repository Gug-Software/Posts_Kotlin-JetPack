package com.gug.example.posts.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gug.example.posts.database.entities.DbComment

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg comments: DbComment)

    @Query("SELECT * FROM  dbcomment WHERE postId = :postId")
    fun getCommentsByPostId(postId: Int): LiveData<List<DbComment>>

    @Query("DELETE FROM dbcomment")
    fun deleAllComments()

}