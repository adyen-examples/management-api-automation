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