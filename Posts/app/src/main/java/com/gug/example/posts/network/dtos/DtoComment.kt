package com.gug.example.posts.network.dtos

import com.gug.example.posts.database.entities.DbComment

data class DtoComment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)

fun List<DtoComment>.asDatabaseModel(): Array<DbComment> {
    return map {
        DbComment(
            id = it.id,
            postId = it.postId,
            body = it.body,
            email = it.email,
            name = it.name
        )
    }.toTypedArray()
}