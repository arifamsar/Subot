package com.subot.convention.utils

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun KotlinDependencyHandler.sharedUiDependencies(project: Project) {
    val libs = project.libs
    api(libs.compose.runtime)
    api(libs.compose.ui)
    api(libs.compose.foundation)
    api(libs.compose.resources)
    api(libs.compose.ui.tooling.preview)
    api(libs.compose.material3)
    api(libs.material.icons.extended)

    implementation(libs.kermit)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.compose.nav3)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil)
    implementation(libs.coil.network.ktor)
    implementation(libs.kotlinx.datetime)
    implementation(libs.materialKolor)
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.material3.adaptive)
    implementation(libs.androidx.material3.adaptive.nav3)
    implementation(libs.androidx.lifecycle.viewmodel.nav3)
    implementation(libs.liquid)
}

fun KotlinDependencyHandler.coreDependencies(project: Project) {
    val libs = project.libs
    api(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kermit)
    implementation(libs.room.runtime)
    implementation(libs.koin.core)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.paging.common)
}
