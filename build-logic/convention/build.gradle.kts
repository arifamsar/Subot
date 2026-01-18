import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.subot.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlin-multiplatform") {
            id = "subot.kotlin.multiplatform"
            implementationClass = "com.subot.convention.KotlinMultiplatformConventionPlugin"
        }
        register("compose-multiplatform") {
            id = "subot.compose.multiplatform"
            implementationClass = "com.subot.convention.ComposeMultiplatformConventionPlugin"
        }
        register("room") {
            id = "subot.room"
            implementationClass = "com.subot.convention.RoomConventionPlugin"
        }
    }
}
