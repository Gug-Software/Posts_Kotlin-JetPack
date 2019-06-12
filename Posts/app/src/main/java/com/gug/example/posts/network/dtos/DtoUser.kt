package com.gug.example.posts.network.dtos

import com.gug.example.posts.database.entities.DbAddress
import com.gug.example.posts.database.entities.DbCompany
import com.gug.example.posts.database.entities.DbGeo
import com.gug.example.posts.database.entities.DbUser
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DtoUser(
    val address: DtoAddress,
    val company: DtoCompany,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

fun DtoUser.asDatabaseModel(): DbUser {
    return DbUser(
        id = this.id,
        email = this.email,
        name = this.name,
        phone = this.phone,
        username = this.username,
        website = this.website,
        company = DbCompany(
            bs = this.company.bs,
            catchPhrase = this.company.catchPhrase,
            name = this.company.name
        ),
        address = DbAddress(
            city = this.address.city,
            street = this.address.street,
            suite = this.address.suite,
            zipcode = this.address.zipcode,
            geo = DbGeo(
                lat = this.address.geo.lat,
                lng = this.address.geo.lng
            )
        )
    )
}