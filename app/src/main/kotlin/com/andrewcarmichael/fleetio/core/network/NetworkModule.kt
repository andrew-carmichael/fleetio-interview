package com.andrewcarmichael.fleetio.core.network

import com.andrewcarmichael.fleetio.core.secrets.FleetIoApiSecrets
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        val apiSecrets: FleetIoApiSecrets = get()
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
                logger = io.ktor.client.plugins.logging.Logger.ANDROID
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            defaultRequest {
                header("Account-Token", apiSecrets.accountToken)
                header("Authorization", apiSecrets.authorizationToken)
            }
        }
    }
}