package com.gug.example.posts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gug.example.posts.database.dao.CommentDao
import com.gug.example.posts.database.dao.PostDao
import com.gug.example.posts.database.dao.UserDao
import com.gug.example.posts.database.entities.DbComment
import com.gug.example.posts.database.entities.DbPost
import com.gug.example.posts.database.entities.DbUser

const val DB_NAME = "Posts"

@Database(
    entities = [DbPost::class, DbComment::class, DbUser::class],
    version = 1
)
abstract class PostsDataBase : RoomDatabase() {

    abstract val postDao: PostDao
    abstract val userDao: UserDao
    abstract val commentDao: CommentDao

    companion object {

        private lateinit var INSTANCE: PostsDataBase

        fun getDatabase(context: Context): PostsDataBase {

            synchronized(PostsDataBase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PostsDataBase::class.java,
                        DB_NAME
                    ).build()
                }
            }

            return INSTANCE

        }
    }
}