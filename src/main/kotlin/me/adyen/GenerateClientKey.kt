package me.adyen

suspend fun main(args: Array<String>){
    generateClientKey("TestCompany123", "ws_211701@Company.TestCompany123")
}

// Just building what I need now, we can dive further into profiles later as we need to expand "B4g[b5S@I,6QzS=LS#rgZpp(."
suspend fun generateClientKey(company: String, user: String) {
    val managementApi = ManagementApi()
    // getting the credentials of the company
    val credentials = managementApi.getapiCredentials(company)
    println(credentials)
    // Generating new Clientkey for specified credential ID and company
    val credential = credentials.data.find { it.username == user}
    if (credential != null) {
        managementApi.generateClientKey(company, credential.id, Specification.Clientkey)
    }
}

