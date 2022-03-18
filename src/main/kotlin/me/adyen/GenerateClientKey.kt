package me.adyen

suspend fun main(args: Array<String>){
    generateClientKey("TestCompany123")
}

// Just building what I need now, we can dive further into profiles later as we need to expand "B4g[b5S@I,6QzS=LS#rgZpp(."
suspend fun generateClientKey(company: String) {
    val managementApi = ManagementApi()
    // getting the credentials ID
    val credentialID = managementApi.getapiCredentials(company)
    println(credentialID)
    // Generating new Clientkey for credential ID and company
    credentialID.data.forEach {
        if (it.username == "ws_211701@Company.TestCompany123") {
            managementApi.generateClientKey(company, it.id)
        }
    }
}

