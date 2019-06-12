package com.gug.example.posts.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gug.example.posts.database.entities.DbUser

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: DbUser)

    @Query("SELECT * FROM  dbuser WHERE id = :userID")
    fun getUserByIdLive(userID: String): LiveData<DbUser>

    @Query("SELECT * FROM  dbuser WHERE id = :userID")
    fun getUserByIdData(userID: String): DbUser

    @Query("DELETE FROM dbuser")
    fun deleteAllUsers()

}