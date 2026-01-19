import com.subot.convention.utils.sharedUiDependencies

plugins {
    alias(libs.plugins.subot.kotlin.multiplatform)
    alias(libs.plugins.subot.compose.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {

    androidLibrary {
        namespace = "com.subot.sharedui"
        compileSdk = libs.versions.android.compileSdk.get().toInt()

        androidResources.enable = true
    }

    sourceSets {
        commonMain.dependencies {
            sharedUiDependencies(project)
            api(projects.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.compose.ui.test)
            implementation(libs.kotlinx.coroutines.test)
        }

        androidMain.dependencies {

        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "subot.sharedui.generated.resources"
    generateResClass = always
}