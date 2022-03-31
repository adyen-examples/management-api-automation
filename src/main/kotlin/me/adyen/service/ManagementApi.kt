package me.adyen

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlin.system.exitProcess

enum class Environment {
    Test,
    Live
}

// Enum class so we can specify between generating clientkey or APIkey
enum class Specification(val value: String) {
    Clientkey("generateClientKey"),
    APIkey("generateApiKey")
}

class ManagementApi(env : Environment = Environment.Test) {
    private val baseUrl = if(env == Environment.Test) "https://management-test.adyen.com/v1" else "https://management.adyen.com/v1"
    private val key: String? = System.getenv("MANAGEMENT_KEY")

    init{
        if (key == null){
            println("No API Key value found for environment variable \$MANAGEMENT_KEY. Exiting...")
            exitProcess(0)

        }
        println(key)
    }

    private val client = HttpClient(){ install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    }

    @OptIn(InternalAPI::class)
    suspend fun createUser(company : String, user : User){
        val response: HttpResponse = client.post("$baseUrl/companies/$company/users") {
            contentType(ContentType.Application.Json)
            body = user
            headers{
                append("x-api-key", key!!)
            }
        }
        println(response.receive<String>())
    }

    @OptIn(InternalAPI::class)
    suspend fun getapiCredentials(company: String): Credentials{
        val response: Credentials = client.get("$baseUrl/companies/$company/apiCredentials") {
                contentType(ContentType.Application.Json)
                headers {
                    append("x-api-key", key!!)
                }
        }
        return response
    }

    @OptIn(InternalAPI::class)
    suspend fun generateClientKey(company: String, apiCredentialID: String, keySpec: Specification): Key{
        return try {
            client.post("$baseUrl/companies/$company/apiCredentials/$apiCredentialID/"+ keySpec.value) {
                contentType(ContentType.Application.Json)
                headers {
                    append("x-api-key", key!!)
                }
            }
        } catch (error: ClientRequestException){
            Key()
        }
    }
}