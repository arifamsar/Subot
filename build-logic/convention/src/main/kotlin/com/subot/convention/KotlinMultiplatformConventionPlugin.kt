package com.subot.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.kotlin.dsl.withType

class KotlinMultiplatformConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("kotlin-multiplatform").get().get().pluginId)
                apply(libs.findPlugin("android-kmp-library").get().get().pluginId)
            }

            // Configure Kotlin Multiplatform extension
            extensions.configure<KotlinMultiplatformExtension> {

                // Configure iOS targets
                listOf(
                    iosArm64(), // for ios devices
                    iosSimulatorArm64(), // for ios simulators in Apple silicon Mac computer
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = path.substring(1).replace(':', '-')
                    }
                }

                //remove expect actual warning
                compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}
