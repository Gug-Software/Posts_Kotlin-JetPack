package com.gug.example.posts.network.dtos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DtoCompany(
    val bs: String, // harness real-time e-markets
    val catchPhrase: String, // Multi-layered client-server neural-net
    val name: String // Romaguera-Crona
)