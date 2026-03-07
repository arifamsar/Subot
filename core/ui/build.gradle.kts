import com.subot.convention.utils.sharedUiDependencies

plugins {
    alias(libs.plugins.subot.kotlin.multiplatform)
    alias(libs.plugins.subot.compose.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    android {
        namespace = "com.subot.core.ui"
        compileSdk = libs.versions.android.compileSdk.get().toInt()

        androidResources.enable = true
    }

    sourceSets {
        commonMain.dependencies {
            sharedUiDependencies(project)
            implementation(project(":core:common"))
        }

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "subot.core.ui.generated.resources"
    generateResClass = always
}
