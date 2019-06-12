package com.gug.example.posts.network.dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DtoAddress(
    val city: String,
    val geo: DtoGeo,
    val street: String,
    val suite: String,
    val zipcode: String
)