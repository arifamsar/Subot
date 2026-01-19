import com.subot.convention.utils.coreDependencies

plugins {
    alias(libs.plugins.subot.kotlin.multiplatform)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.subot.room)
}

kotlin {

    androidLibrary {
        namespace = "com.subot.core"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }
    sourceSets {
        commonMain.dependencies {
            coreDependencies(project)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}