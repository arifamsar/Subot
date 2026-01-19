package com.subot.convention

import com.subot.convention.utils.alias
import com.subot.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {
                alias(libs.plugins.kotlin.multiplatform)
                alias(libs.plugins.android.kmp.library)
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
