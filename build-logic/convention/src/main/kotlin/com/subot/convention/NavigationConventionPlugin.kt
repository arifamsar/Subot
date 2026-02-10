package com.subot.convention

import com.subot.convention.utils.alias
import com.subot.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class NavigationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.kotlinx.serialization)
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain.dependencies {
                    implementation(libs.compose.nav3)
                    implementation(libs.koin.compose.viewmodel.navigation)
                    implementation(libs.kotlinx.serialization.json)
                    implementation(libs.androidx.lifecycle.viewmodel.nav3)
                    implementation(libs.androidx.material3.adaptive)
                    implementation(libs.androidx.material3.adaptive.nav3)
                }
            }
        }
    }
}
