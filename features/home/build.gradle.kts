plugins {
    alias(libs.plugins.subot.kotlin.multiplatform)
}

kotlin {
    androidLibrary {
        namespace = "com.subot.features.home"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
