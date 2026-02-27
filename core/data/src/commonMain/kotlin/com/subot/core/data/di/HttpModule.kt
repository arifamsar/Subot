package com.subot.core.data.di

import Subot.core.data.BuildConfig
import com.subot.core.data.repository.AuthRepositoryImpl
import com.subot.core.data.repository.ListItemRepositoryImpl
import com.subot.core.data.repository.SchoolRepositoryImpl
import com.subot.core.data.service.ApiService
import com.subot.core.data.service.ApiServiceImpl
import com.subot.core.domain.repository.AuthRepository
import com.subot.core.domain.repository.ListItemRepository
import com.subot.core.domain.repository.SchoolRepository
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val httpModule = module {
    single { createHttpClient() }
    single<ApiService> { ApiServiceImpl(get()) }
    single<ListItemRepository> { ListItemRepositoryImpl(get()) }
    single<SchoolRepository> { SchoolRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}

fun createHttpClient(): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                co.touchlab.kermit.Logger.d("HttpClient") { message }
            }

        }
    }
    install(DefaultRequest) {
        url(BuildConfig.BASE_URL)
        header("Accept", "application/json")
        header("Content-Type", "application/json")
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 30_000
        connectTimeoutMillis = 30_000
        socketTimeoutMillis = 30_000
    }
}