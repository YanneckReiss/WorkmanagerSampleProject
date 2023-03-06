package de.yanneckreiss.workmanager_sample_project.data.api

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val TIMEOUT = 30_000

/**
 * For more info refer to my article on Medium:
 * https://medium.com/tech-takeaways/exploring-ktor-an-alternative-to-retrofit-for-http-requests-in-android-7d9eb3219a42
 */
fun provideKtorClient(): HttpClient = HttpClient(Android) {
    engine {
        connectTimeout = TIMEOUT
        socketTimeout = TIMEOUT
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    // Log the message with the framework of your choice
                    Log.d("KtorClient", message)
                }
            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response: HttpResponse ->
                // Log the response with the logging framework of your choice
                Log.d("KtorClient", response.toString())
            }
        }

        install(DefaultRequest) {
            url("https://yourapiplaceholder.com")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
}