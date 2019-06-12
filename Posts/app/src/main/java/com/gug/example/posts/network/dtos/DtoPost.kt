package com.gug.example.posts.network.dtos

import com.gug.example.posts.database.entities.DbPost
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DtoPost(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

fun List<DtoPost>.asDatabaseModel(): Array<DbPost> {
    return map {
        DbPost(
            id = it.id,
            body = it.body,
            title = it.title,
            userId = it.userId,
            read = false,
            favorite = false
        )
    }.toTypedArray()
}