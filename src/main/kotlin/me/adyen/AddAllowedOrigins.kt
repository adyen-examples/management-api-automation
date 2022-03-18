package me.adyen

suspend fun main(args: Array<String>){

    //Specify a domain you want to add as an allowed origin
    val domain = Domain("http://localhost:8585")
    val managementApi = ManagementApi()
    managementApi.addAllowedOrigins(domain)
}