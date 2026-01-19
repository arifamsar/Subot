package com.subot.convention

import androidx.room.gradle.RoomExtension
import com.subot.convention.utils.alias
import com.subot.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.ksp)
                alias(libs.plugins.room)
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                val roomCompiler = libs.room.compiler
                add("kspAndroid", roomCompiler)
                add("kspIosArm64", roomCompiler)
                add("kspIosSimulatorArm64", roomCompiler)
            }
        }
    }
}
