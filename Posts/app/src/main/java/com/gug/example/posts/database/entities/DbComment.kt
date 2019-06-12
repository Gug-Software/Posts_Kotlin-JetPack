package com.gug.example.posts.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gug.example.posts.domain.Comment

@Entity
data class DbComment(

    @PrimaryKey
    val id: Int, // 1

    val postId: Int,
    val body: String, // laudantium enim quasi est quidem magnam voluptate ipsam eostempora quo necessitatibusdolor quam autem quasireiciendis et nam sapiente accusantium
    val email: String, // Eliseo@gardner.biz
    val name: String // id labore ex et quam laborum
)

fun List<DbComment>.asDomainModel(): List<Comment> {
    return map {
        Comment(
            body = it.body,
            email = it.email,
            id = it.id,
            name = it.name,
            postId = it.postId
        )
    }
}