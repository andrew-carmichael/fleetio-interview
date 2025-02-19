package com.andrewcarmichael.fleetio.core.network

import android.util.Log
import com.andrewcarmichael.fleetio.core.secrets.FleetIoApiSecrets
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        FleetioApiBaseUrl(
            hostname = "secure.fleetio.com",
            pathSegments = listOf("api", "v1")
        )
    }

    single {
        val baseUriInfo: FleetioApiBaseUrl = get()
        val apiSecrets: FleetIoApiSecrets = get()
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("KtorClient", message)
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = baseUriInfo.hostname
                    appendPathSegments(baseUriInfo.pathSegments)
                }
                header("Account-Token", apiSecrets.accountToken)
                header("Authorization", apiSecrets.authorizationToken)
            }
        }
    }
}