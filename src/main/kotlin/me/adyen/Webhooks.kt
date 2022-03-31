package me.adyen

suspend fun main(args: Array<String>){

    val managementApi = ManagementApi()
    //Get all the webhooks setup on a company account
    managementApi.getWebhooks("TestCompany123")

    // Generate Hmacs for a specific webhook setup on company account
    managementApi.generateWebhookHmac("TestCompany123", "S2-7474786E4126")

}