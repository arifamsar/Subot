import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "com.subot.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
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
        register("navigation") {
            id = "subot.navigation.convention"
            implementationClass = "com.subot.convention.NavigationConventionPlugin"
        }
        register("feature") {
            id = "subot.feature.convention"
            implementationClass = "com.subot.convention.FeatureConventionPlugin"
        }
    }
}
