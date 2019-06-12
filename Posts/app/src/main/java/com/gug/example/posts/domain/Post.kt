package com.gug.example.posts.domain

import android.os.Parcelable
import com.gug.example.posts.database.entities.DbPost
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    var read: Boolean,
    var favorite: Boolean
) : Parcelable {

}

fun Post.asDataBaseModel(): DbPost {
    return DbPost(
        id = this.id,
        body = this.body,
        title = this.title,
        userId = this.userId,
        read = this.read,
        favorite = this.favorite
    )

}