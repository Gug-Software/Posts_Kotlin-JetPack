package com.gug.example.posts.domain

data class User(
    val id: Int,
    val address: Address,
    val company: Company,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    val userAddress = "${this.address.city}; ${this.address.street}, ${this.address.suite}"
    val userCompany = "${this.company.name}; ${this.company.bs}"
}

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)

data class Address(
    val geo: Geo,
    val city: String,
    val street: String,
    val suite: String,
    val zipcode: String
)

data class Geo(
    val lat: String,
    val lng: String
)