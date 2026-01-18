package com.subot.core.data.di

import com.subot.core.data.repository.ListItemRepositoryImpl
import com.subot.core.data.service.ApiServiceImpl
import com.subot.core.domain.repository.ListItemRepository
import com.subot.core.domain.service.ApiService
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
        level = LogLevel.INFO
    }
    install(DefaultRequest) {
        header("Content-Type", "application/json")
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 30_000
        connectTimeoutMillis = 30_000
        socketTimeoutMillis = 30_000
    }
}