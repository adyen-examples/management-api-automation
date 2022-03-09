import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.Serializable
import kotlin.system.exitProcess

val client = HttpClient(){ install(JsonFeature) {
    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    })
}
}

@Serializable
data class Name(val firstName: String, val lastName: String)

@Serializable
data class AdyenUser(
    val username: String,
    val email : String,
    val name: Name,
    val roles : List<String>,
    val associatedMerchantAccounts : List<String>){
}

val baseUrl = "https://management-test.adyen.com/v1"
val key = System.getenv("MANAGEMENT_KEY")
val companyId = "AdyenRecruitment"
val merchantAccount = "AdyenRecruitmentCOM"

val defaultRoles = listOf(
    "Merchant standard role",
    "Merchant technical integrator",
    "Merchant admin",
    "Manage API credentials",
    "Merchant POS standard role",
//    "View payments"
)

suspend fun main(args: Array<String>){
    println("Hello World!")

    if (key == null){
        println("No API Key value found for environment variable \$MANAGEMENT_KEY. Exiting...")
        exitProcess(0)
    }

    createUser(companyId,
        AdyenUser("testUser",
            "test@adyen.com",
            Name("test", "user"),
            defaultRoles,
            listOf(merchantAccount)
        )
    )

    client.close()
}

@OptIn(InternalAPI::class)
suspend fun createUser(company : String, user : AdyenUser){
    val response: HttpResponse = client.post("$baseUrl/companies/$company/users") {
        contentType(ContentType.Application.Json)
        body = user
        headers{
            append("x-api-key", key)
        }
    }
    println(response.receive<String>())
}