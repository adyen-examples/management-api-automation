package me.adyen

suspend fun main(){
    createEngineeringTestUser("test@adyen.com", "test", "user")
}

// Just building what I need now, we can dive further into profiles later as we need to expand
suspend fun createEngineeringTestUser(email: String, firstName: String, lastName: String){
    val managementApi = ManagementApi()

    val defaultEngineeringTestUserRoles = listOf(
        "Merchant standard role",
        "Merchant technical integrator",
        "Merchant admin",
        "Manage API credentials",
        "Merchant POS standard role",
//    "View payments"
    )

    val companyId = "AdyenRecruitment"
    val merchantAccount = "AdyenRecruitmentCOM"

    managementApi.createUser(companyId,
        User(
            createUsername(firstName, lastName),
            email,
            Name(firstName, lastName),
            defaultEngineeringTestUserRoles,
            listOf(merchantAccount)
        )
    )
}

// Sanitizes the input string to match the Management API recommendations
private fun String.sanitize(): String {
    val regex = Regex.fromLiteral("[a-zA-Z0-9_\\-\\.]+")
    return regex.replace(this,  "")
}

fun createUsername(firstName: String, lastName: String): String {
    return "${firstName.sanitize()}.${lastName.sanitize()}.recruitment"
}