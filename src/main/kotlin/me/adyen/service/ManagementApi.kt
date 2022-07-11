package me.adyen

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import kotlin.system.exitProcess

enum class Environment {
    Test,
    Live
}

class ManagementApi(env: Environment = Environment.Test) {
    private val baseUrl =
        if (env == Environment.Test) "https://management-test.adyen.com/v1" else "https://management.adyen.com/v1"
    private val key: String? = System.getenv("MANAGEMENT_KEY")

    init {
        if (key == null) {
            println("No API Key value found for environment variable \$MANAGEMENT_KEY. Exiting...")
            exitProcess(0)
        }
    }

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun createUser(company: String, user: User) {
        val response: HttpResponse = client.post("$baseUrl/companies/$company/users") {
            contentType(ContentType.Application.Json)
            body = user
            headers {
                append("x-api-key", key!!)
            }
        }
        println(response.body<String>())
    }


}