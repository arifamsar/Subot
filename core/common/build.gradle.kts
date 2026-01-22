plugins {
    alias(libs.plugins.subot.kotlin.multiplatform)
}

kotlin {
    androidLibrary {
        namespace = "com.subot.core.common"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.datetime)
            api(libs.kotlinx.coroutines.core)
            api(libs.kermit)
            implementation(libs.koin.core)
        }
    }
}
