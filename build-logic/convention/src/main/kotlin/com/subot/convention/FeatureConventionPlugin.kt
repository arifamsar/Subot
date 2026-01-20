package com.subot.convention

import com.subot.convention.utils.alias
import com.subot.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.subot.kotlin.multiplatform)
                alias(libs.plugins.subot.compose.multiplatform)
                alias(libs.plugins.subot.navigation.convention)
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain.dependencies {
                    implementation(project(":sharedUI"))
                }
            }
        }
    }
}
