package me.adyen

suspend fun main(args: Array<String>){

    // Give a company name, user and specify which key you want to (re-)generate
    // Change the specification to Specification.APIkey if you want to regenerate the APIkey of that specific user
    generateKey("TestCompany123", "ws_211701@Company.TestCompany123", Specification.Clientkey)
}

suspend fun generateKey(company: String, user: String, spec: Specification) {
    val managementApi = ManagementApi()
    // getting the credentials of the company to get the specific User ID (as of yet it's not easy to find an user ID on CA)
    val credentials = managementApi.getapiCredentials(company)

    // Generating new clientkey for specified credential ID and company
    val credential = credentials.data.find { it.username == user}
    var keys = Key()
    if (credential != null) {
        keys = managementApi.generateClientKey(company, credential.id, spec)
    }
    // Pass your clientkey/APIkey to your application and set the environment variable equal to the new clientkey/APIkey
    //
    println(credentials)
    println(keys.clientKey +" | "+ keys.apiKey)
}

