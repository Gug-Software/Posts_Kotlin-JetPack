package com.gug.example.posts.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gug.example.posts.domain.Post

@Entity
data class DbPost constructor(
    @PrimaryKey
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int,
    val read: Boolean,
    val favorite: Boolean
)

fun List<DbPost>.asDomainModel(): List<Post> {
    return map {
        Post(
            body = it.body,
            id = it.id,
            title = it.title,
            userId = it.userId,
            read = it.read,
            favorite = it.favorite
        )
    }
}

fun DbPost.asDomainModel(): Post {
    return Post(
        body = this.body,
        id = this.id,
        title = this.title,
        userId = this.userId,
        read = this.read,
        favorite = this.favorite
    )

}