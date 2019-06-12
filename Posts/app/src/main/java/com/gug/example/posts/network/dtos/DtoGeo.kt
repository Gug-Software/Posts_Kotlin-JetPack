package com.gug.example.posts.network.dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DtoGeo(
    val lat: String,
    val lng: String
)