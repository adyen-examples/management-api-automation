package me.adyen

import kotlinx.serialization.Serializable

@Serializable
data class Name(val firstName: String, val lastName: String)

@Serializable
data class User(
    val username: String,
    val email : String,
    val name: Name,
    val roles : List<String>,
    val associatedMerchantAccounts : List<String>){
}

@Serializable
data class Domain(
    val domain: String
)

@Serializable
data class Credentials(
    val data: List<Data>
)

@Serializable
data class Data(
    val id: String,
    val username: String
)
@Serializable
data class Key(
    var clientKey: String?=null,
    var apiKey: String?=null
)