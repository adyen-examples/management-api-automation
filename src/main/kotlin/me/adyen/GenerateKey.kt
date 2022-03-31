package me.adyen

suspend fun main(args: Array<String>){
    // Give a company name, user and specify which key you want to (re-)generate
    generateKey("TestCompany123", "ws_211701@Company.TestCompany123", Specification.Clientkey)
}

// Just building what I need now, we can dive further into profiles later as we need to expand
suspend fun generateKey(company: String, user: String, spec: Specification) {
    val managementApi = ManagementApi()
    // getting the credentials of the company
    val credentials = managementApi.getapiCredentials(company)
    println(credentials)

    // Generating new Clientkey for specified credential ID and company
    val credential = credentials.data.find { it.username == user}
    var keys = Key()
    if (credential != null) {
        keys = managementApi.generateClientKey(company, credential.id, spec)
    }
    //
    println(keys.clientKey)
}

