package com.gug.example.posts.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gug.example.posts.domain.Address
import com.gug.example.posts.domain.Company
import com.gug.example.posts.domain.Geo
import com.gug.example.posts.domain.User

@Entity
data class DbUser(

    @PrimaryKey
    val id: Int,

    @Embedded(prefix = "address_")
    val address: DbAddress,

    @Embedded(prefix = "company_")
    val company: DbCompany,

    val email: String, // Sincere@april.biz
    val name: String, // Leanne Graham
    val phone: String, // 1-770-736-8031 x56442
    val username: String, // Bret
    val website: String // hildegard.org

)

data class DbCompany(
    val bs: String, // harness real-time e-markets
    val catchPhrase: String, // Multi-layered client-server neural-net
    val name: String // Romaguera-Crona
)

data class DbAddress(

    @Embedded(prefix = "user_address_geo")
    val geo: DbGeo,

    val city: String, // Gwenborough
    val street: String, // Kulas Light
    val suite: String, // Apt. 556
    val zipcode: String // 92998-3874

)

data class DbGeo(
    val lat: String, // -37.3159
    val lng: String // 81.1496
)

fun DbUser.asDomainModel(): User? {
    if (null == this) return null
    else {
        return User(
            id = this.id,
            email = this.email,
            name = this.name,
            phone = this.phone,
            username = this.username,
            website = this.website,
            company = Company(
                bs = this.company.bs,
                catchPhrase = this.company.catchPhrase,
                name = this.company.name
            ),
            address = Address(
                city = this.address.city,
                street = this.address.street,
                suite = this.address.suite,
                zipcode = this.address.zipcode,
                geo = Geo(
                    lat = this.address.geo.lat,
                    lng = this.address.geo.lng
                )
            )
        )
    }
}